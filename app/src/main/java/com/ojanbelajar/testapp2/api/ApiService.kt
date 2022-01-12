package com.ojanbelajar.testapp2.api

import com.ojanbelajar.testapp2.data.remote.ListFoodResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search.php")
    suspend fun searchFood(@Query("s") query: String): ListFoodResponse

}