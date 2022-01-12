package com.ojanbelajar.testapp2.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ojanbelajar.testapp2.data.Repository
import com.ojanbelajar.testapp2.data.local.entity.CartEntity

class MainViewModel @ViewModelInject constructor(
    private val contentRepository: Repository

) : ViewModel(){
    fun searchFood(q: String) = contentRepository.searchFood(q).asLiveData()

    fun insertCart(cart: CartEntity) = contentRepository.insertCart(cart)
}