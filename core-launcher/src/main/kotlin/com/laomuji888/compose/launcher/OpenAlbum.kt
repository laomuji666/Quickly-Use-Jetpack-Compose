package com.laomuji888.compose.launcher

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable

/**
 * 打开相册
 * 大概有百分之一的手机没有相册权限,会异常,可以try一下.
 */
@Composable
fun openAlbum(photoCallback:(Uri?)->Unit):()->Unit{
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            photoCallback(uri)
        })
    val openCallback: ()->Unit = {
        photoPickerLauncher.launch(
            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly),
        )
    }
    return openCallback
}