package com.example.mealzapp.meals.domain


import com.example.mealzapp.meals.core.NetworkError
import com.example.mealzapp.meals.data.MealsRepository
import com.example.mealzapp.meals.data.local.Meal
import com.example.mealzapp.meals.domain.util.Result
import javax.inject.Inject

class GetMealsByAreaUseCase @Inject constructor(
    private val repository: MealsRepository,
) {

    suspend fun getMealsByArea(
        categoryName: String,
        offset: Int,
        limit: Int
    ): Result<List<Meal>, NetworkError> =
        repository.getMealsByArea(categoryName, offset, limit)
}