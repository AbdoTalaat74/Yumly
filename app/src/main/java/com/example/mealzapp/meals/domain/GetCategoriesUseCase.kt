package com.example.mealzapp.meals.domain

import com.example.mealzapp.meals.core.NetworkError
import com.example.mealzapp.meals.data.MealsRepository
import com.example.mealzapp.meals.data.remote.CategoryResponse
import com.example.mealzapp.meals.domain.util.Result
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val mealsRepository: MealsRepository

) {



    suspend operator fun invoke():Result<CategoryResponse,NetworkError> = mealsRepository.getCategories()
}