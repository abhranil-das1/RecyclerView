package com.example.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.model.Data
import com.example.mvvm.network.DataRepository
import com.example.mvvm.utils.Status
import kotlinx.coroutines.launch

class DataViewModel(private val repository: DataRepository):ViewModel() {

    private val _dataLiveData = MutableLiveData<List<Data>>()
    val dataLiveData: LiveData<List<Data>>
    get() = _dataLiveData

    private val _statusLiveData = MutableLiveData<Status>()
    val statusLiveData: LiveData<Status>
    get() = _statusLiveData

    fun getData(page: Int){
        _statusLiveData.postValue(Status.LOADING)
        viewModelScope.launch {
            val response = repository.getData(page)

            if(response.isSuccessful){
                _dataLiveData.postValue(response.body()?.data)
                _statusLiveData.postValue(Status.SUCCESS)
            }
            else {
                _statusLiveData.postValue(Status.ERROR)
            }
        }
    }
}