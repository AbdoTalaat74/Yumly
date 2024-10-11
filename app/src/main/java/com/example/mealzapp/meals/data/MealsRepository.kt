package com.example.mealzapp.meals.data

import com.example.mealzapp.meals.data.remote.MealsApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealsRepository @Inject constructor(
    private val apiService: MealsApiService
) {

    suspend fun getCategories() = apiService.getCategories().categories
}