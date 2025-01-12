package com.example.mealzapp.meals.domain

import com.example.mealzapp.meals.core.NetworkError
import com.example.mealzapp.meals.data.MealsRepository
import com.example.mealzapp.meals.data.local.Meal
import com.example.mealzapp.meals.domain.util.Result
import javax.inject.Inject

class SearchMealUseCase @Inject constructor(
    private val mealsRepository: MealsRepository
) {
    suspend fun searchMeal(query: String): Result<List<Meal>, NetworkError> =
        mealsRepository.searchMeal(query)
}