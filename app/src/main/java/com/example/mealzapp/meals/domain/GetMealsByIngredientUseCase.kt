package com.example.mealzapp.meals.domain

import com.example.mealzapp.meals.core.NetworkError
import com.example.mealzapp.meals.data.MealsRepository
import com.example.mealzapp.meals.data.local.Meal
import com.example.mealzapp.meals.domain.util.Result
import javax.inject.Inject

class GetMealsByIngredientUseCase @Inject constructor(
    private val mealsRepository: MealsRepository
) {

    suspend fun getMealsByIngredient(
        ingredient: String,
        offset: Int,
        limit: Int
    ): Result<List<Meal>, NetworkError> =
        mealsRepository.getMealsByIngredient(ingredient, offset, limit)

}