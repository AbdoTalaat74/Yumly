package com.example.mealzapp.meals.domain


import com.example.mealzapp.meals.data.MealsRepository
import javax.inject.Inject

class GetMealsByAreaUseCase @Inject constructor(
    private val repository: MealsRepository,
) {

    suspend fun getMealsByArea(categoryName: String,offset:Int,limit:Int) =
        repository.getMealsByArea(categoryName,offset,limit)
}