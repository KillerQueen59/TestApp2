package com.ojanbelajar.testapp2.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ojanbelajar.testapp2.data.Repository

class CartViewModel @ViewModelInject constructor(
    private val repository: Repository
): ViewModel() {

    fun getCart() = repository.getCart().asLiveData()
}