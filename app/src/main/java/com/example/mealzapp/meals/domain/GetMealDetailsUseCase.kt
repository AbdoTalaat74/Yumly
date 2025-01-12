package com.example.mealzapp.meals.domain

import com.example.mealzapp.meals.core.NetworkError
import com.example.mealzapp.meals.data.MealsRepository
import com.example.mealzapp.meals.data.remote.MealsResponse
import com.example.mealzapp.meals.domain.util.Result
import javax.inject.Inject

class GetMealDetailsUseCase @Inject constructor(private val repository: MealsRepository) {

    suspend fun getMealDetails(mealId:Int):Result<MealsResponse,NetworkError> = repository.getMealDetails(mealId)
}