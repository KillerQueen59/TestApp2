package com.ojanbelajar.testapp2.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ojanbelajar.testapp2.data.local.entity.CartEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ContentDao {

    @Query("Select * from carts")
    fun getCart(): Flow<List<CartEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCart(cartEntity: CartEntity)

    @Query("DELETE FROM carts")
    fun deleteCart()
}