package com.example.mealzapp.meals.domain

import com.example.mealzapp.meals.core.NetworkError
import com.example.mealzapp.meals.data.MealsRepository
import com.example.mealzapp.meals.data.remote.MealsResponse
import com.example.mealzapp.meals.domain.util.Result
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(private val mealsRepository: MealsRepository) {
    suspend fun getCountries(): Result<MealsResponse, NetworkError> = mealsRepository.getCountries()
}