package com.example.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.network.DataRepository

class DataViewModelFactory(private val repository: DataRepository)
    :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DataViewModel(repository) as T
    }
}