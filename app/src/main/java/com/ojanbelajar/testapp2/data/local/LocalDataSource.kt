package com.ojanbelajar.testapp2.data.local

import com.ojanbelajar.testapp2.data.local.entity.CartEntity
import com.ojanbelajar.testapp2.data.local.room.ContentDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: ContentDao
): ContentLocalSource{
    override fun getCart(): Flow<List<CartEntity>> = dao.getCart()

    override fun insertCart(cart: CartEntity) = dao.insertCart(cart)

    override fun deleteCart() = dao.deleteCart()

}
