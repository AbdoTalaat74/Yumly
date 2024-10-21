package com.example.mealzapp.meals.data.remote

import com.example.mealzapp.meals.data.local.Meal
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsApiService {

    @GET("categories.php")
    suspend fun getCategories(): CategoryResponse

    @GET("filter.php")
    suspend fun getMealsByCategory(
        @Query("c") categoryName: String
    ): MealsResponse
    @GET("lookup.php")
    suspend fun getMealDetails(
        @Query("i") mealId:Int
    ): MealsResponse

    @GET("filter.php")
    suspend fun getMealsByArea(
        @Query("a") areaName: String
    ): MealsResponse

    @GET("filter.php")
    suspend fun getMealsByIngredient(
        @Query("i") ingredient: String
    ): MealsResponse

    @GET("random.php")
    suspend fun getRandomMeal(): MealsResponse
}


//random.php