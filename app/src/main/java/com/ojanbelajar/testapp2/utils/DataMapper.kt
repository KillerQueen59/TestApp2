package com.ojanbelajar.testapp2.utils

import com.ojanbelajar.testapp2.data.remote.Food
import com.ojanbelajar.testapp2.data.remote.FoodResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.util.concurrent.ThreadLocalRandom


object DataMapper {
    fun mapResponsesToDomainSearch(input: List<FoodResponse>): Flow<List<Food>> {
        val dataArray = ArrayList<Food>()
        input.map {
            val character = Food(
                it.idMeal,
                it.strMeal,
                it.strMealThumb,
                randomPrice()
            )
            dataArray.add(character)
        }
        return flowOf(dataArray)
    }

    private fun randomPrice(): Int  = ThreadLocalRandom.current().nextInt(1000, 100000);
}