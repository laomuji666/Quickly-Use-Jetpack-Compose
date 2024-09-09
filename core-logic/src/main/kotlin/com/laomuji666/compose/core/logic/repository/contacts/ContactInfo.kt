package com.laomuji666.compose.core.logic.repository.contacts

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
import com.laomuji666.compose.core.logic.util.ImageUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

interface ContactInfo{
    val account:Long

    val nickname: String

    val category: String

    val avatar:String

    val avatarPainter: Painter
        @Composable get

    data class FirebaseContactInfo(
        override val account: Long,
        override val nickname: String,
        override val category: String,
        override val avatar: String,
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
                        ImageUtil.getBitmapFromFirebase(context, avatar).collect{
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