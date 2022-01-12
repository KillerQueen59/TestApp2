package com.ojanbelajar.testapp2.data.remote

import com.ojanbelajar.testapp2.api.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService): RemoteDataInterface {
    override suspend fun searchFood(q: String): Flow<ApiResponse<ListFoodResponse>> {
        return flow {
            try {
                val response = apiService.searchFood(q)
                if (!response.equals(null)) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO )
    }


}