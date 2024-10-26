package com.example.mealzapp.meals.domain

import com.example.mealzapp.meals.data.MealsRepository
import javax.inject.Inject

class GetCountriesUseCase@Inject constructor(private val mealsRepository: MealsRepository) {
    suspend fun getCountries() = mealsRepository.getCountries()
}