package com.laomuji1999.compose.launcher

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import com.laomuji1999.compose.core.logic.common.Log
import java.io.File

/**
 * 打开相机
 * 在AndroidManifest.xml里
 * 写了相机权限,就必须有相机权限才能打开,否则异常.
 * 没有写相机权限,可以直接打开,不会异常.
 */
@Composable
fun openCamera(cameraCallback:(Uri?)->Unit): () -> Unit {
    val context = LocalContext.current
    var photoUri by remember { mutableStateOf(value = Uri.EMPTY) }
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) {
                cameraCallback(photoUri)
            }else{
                cameraCallback(null)
            }
        }
    )
    val openCallback: ()->Unit = {
        photoUri = context.createTempPictureUri()
        cameraLauncher.launch(photoUri)
    }
    return openCallback
}

private fun Context.createTempPictureUri(
    provider: String = "${applicationContext.packageName}.provider",
    filename: String = "${System.currentTimeMillis()}"
): Uri? {
    Log.debug("tag_openCamera", provider)
    val tempFile = File(externalCacheDir, filename).apply {
        createNewFile()
    }
    return FileProvider.getUriForFile(applicationContext, provider, tempFile)
}