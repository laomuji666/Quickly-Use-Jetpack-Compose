package com.laomuji666.compose.core.logic.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.laomuji666.compose.core.logic.database.dao.ContactDao
import com.laomuji666.compose.core.logic.database.entity.ContactInfoEntity

@Database(
    entities = [
        ContactInfoEntity::class
    ],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract fun contactDao(): ContactDao
}