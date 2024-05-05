package com.example.fitlife.repositories

import com.example.fitlife.database.FitLifeDataSource
import com.example.fitlife.model.FitLifeItemEntity
import javax.inject.Inject

class FitLifeRepository @Inject constructor(
    private val dataSource: FitLifeDataSource
) {

    fun getItems() = dataSource.getItems()

    suspend fun addItem(item: FitLifeItemEntity) = dataSource.addItem(item)

    suspend fun removeItem(item: FitLifeItemEntity) = dataSource.removeItem(item)
}