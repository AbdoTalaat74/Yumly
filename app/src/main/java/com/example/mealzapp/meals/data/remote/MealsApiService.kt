package com.example.mealzapp.meals.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface MealsApiService {

    @GET("categories.php")
    suspend fun getCategories(): CategoryResponse

    @GET("filter.php")
    suspend fun getMealsByCategory(
        @Query("c") categoryName: String
    ): MealsResponse

}