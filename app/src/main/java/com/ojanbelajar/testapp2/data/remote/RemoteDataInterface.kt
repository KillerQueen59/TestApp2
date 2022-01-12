package com.ojanbelajar.testapp2.data.remote

import kotlinx.coroutines.flow.Flow

interface RemoteDataInterface {
    suspend fun searchFood(q: String): Flow<ApiResponse<ListFoodResponse>>
}
