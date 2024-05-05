package com.example.fitlife.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitlife.model.FitLifeItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FitLifeDao {

    @Query("SELECT * FROM fitlife_items")
    fun getItems(): Flow<List<FitLifeItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(item: FitLifeItemEntity)

    @Delete
    suspend fun removeItem(item: FitLifeItemEntity)
}