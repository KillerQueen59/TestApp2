package com.ojanbelajar.testapp2.data.local

import com.ojanbelajar.testapp2.data.local.entity.CartEntity
import kotlinx.coroutines.flow.Flow

interface ContentLocalSource {
    fun getCart(): Flow<List<CartEntity>>

    fun insertCart(cart: CartEntity)

    fun deleteCart()
}
