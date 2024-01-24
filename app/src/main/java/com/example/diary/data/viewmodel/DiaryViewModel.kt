package com.example.diary.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.diary.data.database.DiaryDatabase
import com.example.diary.data.models.DiaryEntity
import com.example.diary.data.repository.DiaryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DiaryViewModel(application: Application): AndroidViewModel(application) {

    private val diaryDao = DiaryDatabase.getDatabase(application).diaryDao()
    private val repository: DiaryRepository

    private val getAllData: LiveData<List<DiaryEntity>>

    init {
        repository = DiaryRepository(diaryDao)
        getAllData = repository.getAllData
    }

    fun insertData(diaryEntity: DiaryEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(diaryEntity)
        }
    }
}