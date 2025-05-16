package com.laomuji888.compose.core.logic.database.entity

import android.net.Uri
import androidx.core.net.toUri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ContactInfoEntity(
    @PrimaryKey var account: Long,
    var nickname: String,
    var category: String,
    var avatar: String
) {
    val contentUri: Uri
        get() = "laomuji://compose.laomuji888.com/chat/$account".toUri()

    val avatarUri: Uri
        get() = avatar.toUri()
}