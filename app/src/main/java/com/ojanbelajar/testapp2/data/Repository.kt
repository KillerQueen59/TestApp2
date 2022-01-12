package com.ojanbelajar.testapp2.data


import com.ojanbelajar.testapp2.data.local.entity.CartEntity
import com.ojanbelajar.testapp2.data.remote.Food
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun searchFood(q: String): Flow<Resource<List<Food>>>

    fun insertCart(cartEntity: CartEntity)

    fun getCart(): Flow<Resource<List<CartEntity>>>

    fun deleteCart()
}