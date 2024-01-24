package com.example.diary.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.diary.data.models.Priority

@Entity(tableName = "diary_table")
data class DiaryEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var date: String,
    var priority: Priority,
    var description: String
)