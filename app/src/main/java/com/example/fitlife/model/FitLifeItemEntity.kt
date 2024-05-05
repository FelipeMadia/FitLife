package com.example.fitlife.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fitlife_items")
data class FitLifeItemEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val type: Long,
    val title: String,
    val duration: String?= null,
    val aliments: String?= null,
    val calories: Long,
    val obs: String? = null,
    val image: Bitmap? = null,
    val date: String
)