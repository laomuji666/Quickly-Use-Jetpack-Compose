package com.laomuji666.compose.core.logic.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.laomuji666.compose.core.logic.database.entity.YoutubeDLInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface YoutubeDLDao {
    @Query("SELECT * FROM YoutubeDLInfoEntity ORDER BY id DESC")
    fun getYoutubeDLInfoList(): Flow<List<YoutubeDLInfoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(youtubeDLInfoEntity: YoutubeDLInfoEntity)
}