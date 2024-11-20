package com.laomuji666.compose.core.logic.location

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Looper
import android.provider.Settings
import androidx.core.content.ContextCompat
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.laomuji666.compose.core.logic.common.Log
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

/**
 * 至少需要 ACCESS_COARSE_LOCATION 权限才能使用
 * 两种方式获取定位
 * 1. FusedLocationProviderClient
 * 2. LocationManager
 * FusedLocationProviderClient 的准确性更高,但没有ACCESS_FINE_LOCATION权限时无法主动获取定位,只能被动接收位置,经常会出现null.
 * LocationManager 的准确性较低,但可以指定获取什么类型的定位,作为备选方案,只通过网络获取定位.
 * 只有在 FusedLocationProviderClient 无法获取定位的情况才会使用 LocationManager
 */
class DefaultLocator @Inject constructor(
    private val application: Application
) : Locator {

    companion object {
        private const val TAG = "tag_locator"
    }

    private val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(application)

    private val locationManager = application.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    override fun isEnableGps():Boolean{
        return hasGpsProvider() || hasNetworkProvider()
    }

    override fun openGpsSetting(context: Context){
        val intent = Intent()
        intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS)

        //application context 必须加上 intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        //如果不加上 会导致找不到activity栈 导致闪退
        if(context is Application){
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        context.startActivity(intent)
    }

    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): Location? {
        if(!isEnableGps()){
            Log.debug(TAG, "isEnableGps false")
            return null
        }
        if(!hasCoarseLocationPermission() && !hasFineLocationPermission()){
            Log.debug(TAG, "hasCoarseLocationPermission && hasFineLocationPermission false")
            return null
        }
        val priority = if(hasFineLocationPermission()){
            Log.debug(TAG, "use PRIORITY_HIGH_ACCURACY")
            Priority.PRIORITY_HIGH_ACCURACY
        }else{
            Log.debug(TAG, "use PRIORITY_BALANCED_POWER_ACCURACY")
            Priority.PRIORITY_BALANCED_POWER_ACCURACY
        }
        return suspendCancellableCoroutine { cont ->
            fusedLocationProviderClient.getCurrentLocation(CurrentLocationRequest.Builder().apply {
                setPriority(priority)
                setDurationMillis(10 * 1000)
                setMaxUpdateAgeMillis(0)
            }.build(), CancellationTokenSource().token).apply {
                fusedLocationProviderClient.locationAvailability.addOnSuccessListener {
                    if(it.isLocationAvailable){
                        Log.debug(TAG,"isLocationAvailable is true, use fusedLocationProviderClient")
                    }else{
                        Log.debug(TAG,"isLocationAvailable is false, use locationManager")
                        requestNetworkLocation{ location ->
                            cont.safeResume(location)
                        }
                    }
                }
                Log.debug(TAG,"isComplete $isComplete")
                if(isComplete){
                    if(isSuccessful){
                        Log.debug(TAG,"successful")
                        cont.safeResume(result)
                    }else{
                        Log.debug(TAG,"unsuccessful")
                        cont.safeResume(null)
                    }
                }else{
                    addOnSuccessListener {
                        Log.debug(TAG,"onSuccess $it")
                        cont.safeResume(it)
                    }
                    addOnFailureListener {
                        Log.debug(TAG,"onFailure")
                        cont.safeResume(null)
                    }
                    addOnCanceledListener {
                        Log.debug(TAG,"onCancel")
                        cont.cancel()
                    }
                }
            }
        }
    }

    private fun hasGpsProvider() = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

    private fun hasNetworkProvider() = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

    private fun hasCoarseLocationPermission() = ContextCompat.checkSelfPermission(application, ACCESS_COARSE_LOCATION) == PERMISSION_GRANTED

    private fun hasFineLocationPermission() = ContextCompat.checkSelfPermission(application, ACCESS_FINE_LOCATION) == PERMISSION_GRANTED

    private fun <T>CancellableContinuation<T>.safeResume(value: T) {
        if (isActive) {
            resume(value)
        }
    }

    private fun requestNetworkLocation(
        callback:(Location)->Unit
    ){
        val locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                Log.debug(TAG, "requestNetworkLocation $location")
                callback(location)
                locationManager.removeUpdates(this)
            }
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }
        if(hasNetworkProvider()){
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                0,
                0f,
                locationListener,
                Looper.getMainLooper()
            )
        }
    }
}