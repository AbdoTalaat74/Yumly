package com.example.mealzapp.meals.domain

import com.example.mealzapp.meals.data.MealsRepository
import javax.inject.Inject


class GetRandomMealUseCase@Inject constructor(
   private val mealsRepository: MealsRepository
) {

    suspend operator fun invoke() = mealsRepository.getRandomMeal()
}