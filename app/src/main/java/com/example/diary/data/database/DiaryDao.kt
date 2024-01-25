package com.example.diary.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.diary.data.models.DiaryEntity

@Dao
interface DiaryDao {

    @Query("SELECT * FROM diary_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<DiaryEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(diaryEntity: DiaryEntity)

    @Update
    suspend fun updateData(diaryEntity: DiaryEntity)

    @Delete
    suspend fun deleteItem(diaryEntity: DiaryEntity)

}