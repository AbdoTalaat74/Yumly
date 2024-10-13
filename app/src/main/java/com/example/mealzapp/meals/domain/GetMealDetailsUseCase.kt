package com.example.mealzapp.meals.domain

import com.example.mealzapp.meals.data.MealsRepository
import javax.inject.Inject

class GetMealDetailsUseCase @Inject constructor(private val repository: MealsRepository) {

    suspend fun getMealDetails(mealId:Int) = repository.getMealDetails(mealId)
}