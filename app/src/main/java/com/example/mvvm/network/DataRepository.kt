package com.example.mvvm.network

class DataRepository(private val service: DataService) {

    suspend fun getData(page:Int) =
        service.getData(page)
}