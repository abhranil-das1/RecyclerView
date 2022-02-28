package com.example.mvvm.model

data class DataResponse(
    val data:List<Data>,
    val support:Support,
    val page:Int,
    val per_page:Int,
    val total:Int,
    val total_pages:Int
)
