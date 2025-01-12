package com.example.mealzapp.meals.domain

import com.example.mealzapp.meals.core.NetworkError
import com.example.mealzapp.meals.data.MealsRepository
import com.example.mealzapp.meals.data.local.Meal
import com.example.mealzapp.meals.domain.util.Result
import javax.inject.Inject

class GetMealsByCategoryUseCase @Inject constructor(
    private val repository: MealsRepository,
) {

    suspend fun getMealsByCategory(
        categoryName: String,
        offset: Int,
        limit: Int
    ): Result<List<Meal>, NetworkError> =
        repository.getMealsByCategory(categoryName, offset, limit)
}