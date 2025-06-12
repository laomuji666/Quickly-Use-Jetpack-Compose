package com.laomuji1999.compose.launcher

import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

/**
 * 选择联系人
 * 不需要权限
 * 姓名可能为空
 */
@Composable
fun openContact(contactCallback:(SelectContactInfo)->Unit): () -> Unit {
    val context = LocalContext.current
    val contactPickLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.let { intent ->
                    intent.data?.let {uri ->
                        contactCallback(
                            getContactInfo(
                                context = context,
                                uri = uri
                            )
                        )
                    }
                }
            }
        })
    val openCallback: ()->Unit = {
        contactPickLauncher.launch(Intent(Intent.ACTION_PICK).apply {
            setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE)
        })
    }
    return openCallback

}

data class SelectContactInfo(
    val name: String,
    val mobile: String,
)
private fun getContactInfo(context: Context, uri: Uri): SelectContactInfo {
    val contentResolver: ContentResolver = context.contentResolver
    val cursor = contentResolver.query(uri, null, null, null, null) ?: return SelectContactInfo("","")
    cursor.moveToFirst()
    val namePosition = cursor.getColumnIndex("display_name")
    val mobilePosition = cursor.getColumnIndex("data1")
    val selectContactInfo = SelectContactInfo(
        name = cursor.getString(namePosition)?:"",
        mobile = cursor.getString(mobilePosition)
    )
    cursor.close()
    return selectContactInfo
}