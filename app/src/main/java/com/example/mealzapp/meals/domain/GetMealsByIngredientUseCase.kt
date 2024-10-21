package com.example.mealzapp.meals.domain

import com.example.mealzapp.meals.data.MealsRepository
import javax.inject.Inject

class GetMealsByIngredientUseCase @Inject constructor(
    private val mealsRepository: MealsRepository
) {

    suspend fun getMealsByIngredient(ingredient: String) =
        mealsRepository.getMealsByIngredient(ingredient)

}