package com.laomuji666.compose.core.logic.http

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import androidx.core.content.getSystemService
import com.laomuji666.compose.core.logic.common.Log
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * 必须要有 ACCESS_NETWORK_STATE 权限
 */
class ConnectivityObserver(
    context: Context
) {
    companion object {
        const val TAG = "tag_ConnectivityObserver"

        /**
         * 网络有效验证.
         * 因为存在内网的情况,所以默认不开启,只要用户开启了网络就认为有网络.
         */
        const val VALIDATED = false
    }

    private val connectivityManager = context.getSystemService<ConnectivityManager>()!!
    val isConnected: Flow<Boolean>
        get() = callbackFlow {
            val callback = object : NetworkCallback() {
                override fun onCapabilitiesChanged(
                    network: Network,
                    networkCapabilities: NetworkCapabilities
                ) {
                    super.onCapabilitiesChanged(network, networkCapabilities)
                    if (VALIDATED) {
                        val connected =
                            networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
                        Log.debug(TAG, "onCapabilitiesChanged: $connected")
                        trySend(connected)
                    }
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    Log.debug(TAG, "onUnavailable")
                    trySend(false)
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    Log.debug(TAG, "onLost")
                    trySend(false)
                }

                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    Log.debug(TAG, "onAvailable")
                    trySend(true)
                }
            }

            connectivityManager.registerDefaultNetworkCallback(callback)

            awaitClose {
                connectivityManager.unregisterNetworkCallback(callback)
            }
        }
}