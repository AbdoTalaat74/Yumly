package com.example.mealzapp.meals.domain

import com.example.mealzapp.meals.data.MealsRepository
import com.example.mealzapp.meals.data.local.Category
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val mealsRepository: MealsRepository

) {



    suspend operator fun invoke() = mealsRepository.getCategories()
}