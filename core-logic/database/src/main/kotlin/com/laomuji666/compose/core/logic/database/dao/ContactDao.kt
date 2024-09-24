package com.laomuji666.compose.core.logic.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.laomuji666.compose.core.logic.database.entity.ContactInfoEntity

@Dao
interface ContactDao {
    @Query("SELECT * FROM ContactInfoEntity")
    fun getAll(): List<ContactInfoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<ContactInfoEntity>)
}