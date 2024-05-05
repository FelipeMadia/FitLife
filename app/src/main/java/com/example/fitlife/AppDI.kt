package com.example.fitlife

import android.content.Context
import androidx.room.Room
import com.example.fitlife.database.FitLifeDao
import com.example.fitlife.database.FitLifeDatabase
import com.example.fitlife.database.FitLifeDataSource
import com.example.fitlife.repositories.FitLifeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppDI {

    @Provides
    @Singleton
    fun provideFitLifeDao(fitLifeDatabase: FitLifeDatabase): FitLifeDao {
        return fitLifeDatabase.fitLifeDao()
    }

    @Provides
    @Singleton
    fun provideFitLiferepository(fitLifeDataSource: FitLifeDataSource) =
        FitLifeRepository(fitLifeDataSource)

    @Provides
    @Singleton
    fun provideFitLifeDatabase(
        @ApplicationContext applicationContext: Context
    ): FitLifeDatabase {
        return Room.databaseBuilder(
            applicationContext,
            FitLifeDatabase::class.java,
            "fitlife_database"
        ).build()
    }
}