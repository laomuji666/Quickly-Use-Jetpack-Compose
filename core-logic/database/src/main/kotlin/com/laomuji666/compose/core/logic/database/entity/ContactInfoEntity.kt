package com.laomuji666.compose.core.logic.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ContactInfoEntity(
    @PrimaryKey var account:Long,
    var nickname: String,
    var category: String,
    var avatar: String
)