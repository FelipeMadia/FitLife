package com.example.fitlife.ui

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitlife.model.FitLifeItemEntity
import com.example.fitlife.repositories.FitLifeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: FitLifeRepository
): ViewModel() {

    val allItems: Flow<List<FitLifeItemEntity>> = repository.getItems()

    fun addItem(item: FitLifeItemEntity) {
        viewModelScope.launch {
            repository.addItem(item)
        }
    }

    fun removeItem(item: FitLifeItemEntity) {
        viewModelScope.launch {
            repository.removeItem(item)
        }
    }

//    fun updateItem(itemId: Long) {
//        viewModelScope.launch {
//            repository.updateItem(itemId)
//        }
//    }
}