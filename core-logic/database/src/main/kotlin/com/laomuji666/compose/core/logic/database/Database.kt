package com.laomuji666.compose.core.logic.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.laomuji666.compose.core.logic.database.dao.ContactDao
import com.laomuji666.compose.core.logic.database.dao.MessageDao
import com.laomuji666.compose.core.logic.database.entity.ContactInfoEntity
import com.laomuji666.compose.core.logic.database.entity.MessageInfoEntity

@Database(
    entities = [
        ContactInfoEntity::class,
        MessageInfoEntity::class
    ],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract fun contactDao(): ContactDao

    abstract fun messageDao(): MessageDao
}