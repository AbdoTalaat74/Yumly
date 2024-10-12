package com.example.mealzapp.meals.domain

import com.example.mealzapp.meals.data.MealsRepository
import javax.inject.Inject

class GetMealsByCategoryUseCase @Inject constructor(
    private val repository: MealsRepository,
) {

    suspend fun getMealsByCategory(categoryName: String,offset: Int,limit:Int) =
        repository.getMealsByCategory(categoryName,offset,limit)
}