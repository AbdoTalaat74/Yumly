package com.example.mealzapp.meals.domain

import com.example.mealzapp.meals.data.MealsRepository
import javax.inject.Inject

class SearchMealUseCase@Inject constructor(
    private val mealsRepository: MealsRepository
) {
    suspend fun searchMeal(query:String) = mealsRepository.searchMeal(query)
}