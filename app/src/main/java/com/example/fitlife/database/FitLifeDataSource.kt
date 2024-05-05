package com.example.fitlife.database

import javax.inject.Inject
import com.example.fitlife.model.FitLifeItemEntity

class FitLifeDataSource @Inject constructor(
    private val fitLifeDao: FitLifeDao
) {

    fun getItems() = fitLifeDao.getItems()

    suspend fun addItem(item: FitLifeItemEntity) = fitLifeDao.addItem(
        if (item.type.toInt() == 1) {
            FitLifeItemEntity(
                type = item.type,
                title = item.title,
                duration = item.duration,
                calories = item.calories,
                obs = item.obs,
                date = item.date
            )
        } else {
            FitLifeItemEntity(
                type = item.type,
                title = item.title,
                aliments = item.aliments,
                calories = item.calories,
                obs = item.obs,
                image = item.image.takeIf { it != null },
                date = item.date
            )
        }
    )

    suspend fun removeItem(item: FitLifeItemEntity) = fitLifeDao.removeItem(item)

}