package com.example.diary.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "diary_table")
@Parcelize
data class DiaryEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var date: String,
    var priority: Priority,
    var description: String
): Parcelable