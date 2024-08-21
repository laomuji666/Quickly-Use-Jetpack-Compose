package com.laomuji666.compose

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class SimpleFirebaseMessagingService : FirebaseMessagingService(){
    companion object {
        const val TAG = "tag_firebase_messaging"
    }

    /**
     * 收到FCM消息的入口
     */
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        //收到推送消息
        Log.d(TAG, "收到推送消息 : ${remoteMessage.from}")

        //是否携带数据
        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "通知携带数据 : ${remoteMessage.data}")
            //对携带的数据进行检查,是需要执行长时间的任务,还是短时间的任务
            //十秒之内可以完成的任务就是短时间的任务
            //长时间的任务需要导入 androidx.work:work-runtime
        }

        //是否需要显示顶部通知,这里的通知需要手动触发
        remoteMessage.notification?.let {
            //没有标题和内容,不显示
            if(it.title==null && it.body == null){
                Log.d(TAG, "不显示通知,内容为空")
                return
            }
            Log.d(TAG, "显示通知")
            val title = it.title ?: getString(R.string.app_name)
            sendNotification(title, it.body?:title, it.imageUrl)
        }
    }

    /**
     * 推送token发生变动时触发
     * 首次打开app 会自动生成,也会触发
     */
    override fun onNewToken(token: String) {
        Log.d(TAG, "token 发生变化 : $token")
        //可以发送一个请求,把新的token提交给后端 , 也可以不在这里发,每次打开app发送也可以
        //甚至不处理也可以,只有卸载,清除缓存,新设备恢复旧设备 才会更新token
    }

    /**
     * 发送通知消息
     */
    private fun sendNotification(title:String, messageBody: String, imageUrl: Uri?) {
        val requestCode = (System.currentTimeMillis() % 1000).toInt()
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this,
            requestCode,
            intent,
            PendingIntent.FLAG_IMMUTABLE,
        )

        //当前通知渠道的id
        val channelId = "FCM_CHANNEL"
        //在设置里的通知渠道描述
        val channelDescription = "FCM Channel"

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        //显示图片,在前台就是小图,在后台就是大图
        val loadBitmap = loadBitmap(imageUrl)
        loadBitmap?.let {
            notificationBuilder.setLargeIcon(it)
        }

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                channelDescription,
                NotificationManager.IMPORTANCE_DEFAULT,
            )
            notificationManager.createNotificationChannel(channel)
        }

        //同一个app相同的 notifyId 会被覆盖
        val notifyId = System.currentTimeMillis() % 1000
        notificationManager.notify(notifyId.toInt(), notificationBuilder.build())
    }

    /**
     * 简单从网络加载图片
     */
    private fun loadBitmap(imageUrl: Uri?): Bitmap? {
        if(imageUrl == null){
            return null
        }
        val inputStream: InputStream
        try {
            val url = URL(imageUrl.toString())
            Log.d(TAG,"image url : $url")
            val connection =
                url.openConnection() as HttpURLConnection
            connection.setDoInput(true)
            connection.connect()
            inputStream = connection.inputStream
            return BitmapFactory.decodeStream(inputStream)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
}