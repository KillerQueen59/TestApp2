package com.ojanbelajar.testapp2.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ojanbelajar.testapp2.data.Repository
import com.ojanbelajar.testapp2.data.local.entity.CartEntity

class ReceiptViewModel @ViewModelInject constructor(
    private val contentRepository: Repository

) : ViewModel(){
   fun deleteCart() = contentRepository.deleteCart()
}