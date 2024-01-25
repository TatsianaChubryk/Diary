package com.example.diary.data.repository

import androidx.lifecycle.LiveData
import com.example.diary.data.database.DiaryDao
import com.example.diary.data.models.DiaryEntity

class DiaryRepository(private val diaryDao: DiaryDao) {

    val getAllData: LiveData<List<DiaryEntity>> = diaryDao.getAllData()

    suspend fun insertData(diaryEntity: DiaryEntity){
        diaryDao.insertData(diaryEntity)
    }

    suspend fun updateData(diaryEntity: DiaryEntity){
        diaryDao.updateData(diaryEntity)
    }
}