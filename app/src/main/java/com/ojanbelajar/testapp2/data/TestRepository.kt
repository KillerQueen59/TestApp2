package com.ojanbelajar.testapp2.data

import com.ojanbelajar.testapp2.data.local.ContentLocalSource
import com.ojanbelajar.testapp2.data.local.entity.CartEntity
import com.ojanbelajar.testapp2.data.remote.*
import com.ojanbelajar.testapp2.utils.AppExecutors
import com.ojanbelajar.testapp2.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TestRepository  @Inject constructor(
    private val contentRemoteSource: RemoteDataInterface,
    private val contentLocalSource: ContentLocalSource,
    private val appExecutors: AppExecutors
): Repository {
    override fun searchFood(q: String): Flow<Resource<List<Food>>>  =
        object : NetworkOnlyResource<List<Food>, ListFoodResponse>(){
            override fun loadFromNetwork(data: ListFoodResponse): Flow<List<Food>> {
                return DataMapper.mapResponsesToDomainSearch(data.meals)
            }

            override suspend fun createCall(): Flow<ApiResponse<ListFoodResponse>> {
                return contentRemoteSource.searchFood(q)
            }

        }.asFlow()

    override fun insertCart(cartEntity: CartEntity) {
        appExecutors.diskIO().execute {
            contentLocalSource.insertCart(cartEntity)
        }
    }

    override fun getCart(): Flow<Resource<List<CartEntity>>> =
        object : NetworkBoundResource<List<CartEntity>,ListFoodResponse>(){
            override fun loadFromDB(): Flow<List<CartEntity>> {
                return contentLocalSource.getCart()
            }

            override fun shouldFetch(data: List<CartEntity>?): Boolean = false

            override suspend fun createCall(): Flow<ApiResponse<ListFoodResponse>> {
                return contentRemoteSource.searchFood("")
            }

            override suspend fun saveCallResult(data: ListFoodResponse) {

            }

        }.asFlow()

    override fun deleteCart() {
        appExecutors.diskIO().execute {
            contentLocalSource.deleteCart()
        }
    }


}