package com.example.mealzapp.meals.data

import com.example.mealzapp.meals.data.local.Meal
import com.example.mealzapp.meals.data.remote.MealsApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealsRepository @Inject constructor(
    private val apiService: MealsApiService
) {

    suspend fun getCategories() = apiService.getCategories().categories

    suspend fun getMealsByCategory(categoryName:String,offset: Int,limit:Int) :List<Meal> {
       val allMeals = apiService.getMealsByCategory(categoryName).meals
        return allMeals.drop(offset).take(limit)
    }

    suspend fun getMealDetails(mealId:Int) = apiService.getMealDetails(mealId)
}