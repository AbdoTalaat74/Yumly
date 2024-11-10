package com.example.mealzapp.meals.domain

import androidx.compose.ui.geometry.Offset
import com.example.mealzapp.meals.data.MealsRepository
import javax.inject.Inject

class GetIngredientsUseCase @Inject constructor(
    private val mealsRepository: MealsRepository
) {

    suspend fun getIngredients() = mealsRepository.getIngredients()

}