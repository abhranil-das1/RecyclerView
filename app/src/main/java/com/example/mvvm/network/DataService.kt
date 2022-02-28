package com.example.mvvm.network

import com.example.mvvm.model.DataResponse
import com.example.mvvm.utils.BASE_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface DataService {

    @GET("users")
    suspend fun getData(
        @Query("page") page: Int
        ): Response<DataResponse>

    companion object{
        var retrofit:Retrofit?= null

        fun getRetrofit() : DataService{
            if(retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!.create(DataService::class.java)
        }
    }

}