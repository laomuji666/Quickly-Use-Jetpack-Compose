package com.laomuji666.compose.core.logic.util

import android.content.Context
import android.graphics.BitmapFactory
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

object ImageUtil {
    fun getBitmapFromFirebase(context: Context, filename:String) = flow{
        val filepath = File(context.cacheDir, filename)
        if(!filepath.exists()){
            val downloadUrl = Firebase.storage.reference.child(filename).downloadUrl.await().toString()
            downloadFileFromUrl(
                downloadUrl = downloadUrl,
                filename = filepath.absolutePath
            )
        }
        emit(BitmapFactory.decodeFile(filepath.absolutePath))
    }

    private fun downloadFileFromUrl(downloadUrl: String, filename: String):File?{
        val inputStream: InputStream
        try {
            val url = URL(downloadUrl)
            val connection = url.openConnection() as HttpURLConnection
            connection.setDoInput(true)
            connection.connect()
            inputStream = connection.inputStream
            val file = File(filename)
            file.writeBytes(inputStream.readBytes())
            return file
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
}