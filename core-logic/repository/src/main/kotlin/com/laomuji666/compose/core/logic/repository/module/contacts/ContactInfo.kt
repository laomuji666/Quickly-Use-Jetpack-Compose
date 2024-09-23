package com.laomuji666.compose.core.logic.repository.module.contacts

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

interface ContactInfo{
    val account:Long

    val nickname: String

    val category: String

    val avatarPainter: Painter
        @Composable get

    data class InnerContactInfo(
        override val account: Long,
        override val nickname: String,
        override val category: String,
        val resId: Int
    ) : ContactInfo {
        override val avatarPainter: Painter
            @Composable get() = painterResource(id = resId)
    }

    data class FirebaseContactInfo(
        override val account: Long,
        override val nickname: String,
        override val category: String,
        val avatar: String,
    ) : ContactInfo {
        constructor(map:Map<String,*>) : this(
            account = map["account"] as Long,
            nickname = map["nickname"] as String,
            category = map["category"] as String,
            avatar = map["avatar"] as String,
        )

        override val avatarPainter: Painter
            @Composable get(){
                var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
                val context = LocalContext.current
                LaunchedEffect(Unit) {
                    CoroutineScope(Dispatchers.IO).launch {
                        getBitmapFromFirebase(context, avatar).collect{
                            imageBitmap = it?.asImageBitmap()
                        }
                    }
                }
                return if(imageBitmap!=null){
                    BitmapPainter(imageBitmap!!)
                }else{
                    rememberVectorPainter(image = Icons.Default.Refresh)
                }
            }
    }
}

fun List<ContactInfo>.getTypeList():List<String>{
    val result:MutableSet<String> = mutableSetOf()
    forEach {
        result.add(it.category)
    }
    return result.toList()
}

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