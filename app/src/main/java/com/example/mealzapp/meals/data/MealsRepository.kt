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

    suspend fun getMealsByCategory(categoryName:String) :List<Meal> {
       val allMeals = apiService.getMealsByCategory(categoryName).meals
        return allMeals
    }

    suspend fun getMealDetails(mealId:Int) = apiService.getMealDetails(mealId)

    suspend fun getMealsByArea(areaName:String) :List<Meal>{
        val allMeals = apiService.getMealsByArea(areaName).meals
        return allMeals
    }

    suspend fun getMealsByIngredient(ingredient:String) : List<Meal> {
        val allMeals = apiService.getMealsByIngredient(ingredient).meals
        return allMeals
    }

    suspend fun getRandomMeal() = apiService.getRandomMeal().meals
}