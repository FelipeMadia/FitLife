package com.example.fitlife.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.fitlife.model.FitLifeItemEntity
import com.example.fitlife.utils.Converters

@TypeConverters(Converters::class)
@Database(entities = [FitLifeItemEntity::class], version = 1)
abstract class FitLifeDatabase: RoomDatabase() {
    abstract fun fitLifeDao(): FitLifeDao
}