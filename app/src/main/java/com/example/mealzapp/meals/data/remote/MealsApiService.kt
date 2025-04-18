package com.example.mealzapp.meals.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsApiService {

//    @GET("categories.php")
//    suspend fun getCategories(): CategoryResponse

    @GET("categories.php")
    suspend fun getCategories(): Response<CategoryResponse>


    @GET("filter.php")
    suspend fun getMealsByCategory(
        @Query("c") categoryName: String
    ): Response<MealsResponse>
    @GET("lookup.php")
    suspend fun getMealDetails(
        @Query("i") mealId:Int
    ): Response<MealsResponse>

    @GET("filter.php")
    suspend fun getMealsByArea(
        @Query("a") areaName: String
    ): Response<MealsResponse>

    @GET("filter.php")
    suspend fun getMealsByIngredient(
        @Query("i") ingredient: String
    ): Response<MealsResponse>

    @GET("random.php")
    suspend fun getRandomMeal(): Response<MealResponse>

    @GET("list.php?i=list")
    suspend fun getIngredients(): Response<MealsResponse>

    @GET("list.php?a=list")
    suspend fun getCountries(): Response<MealsResponse>

    @GET("search.php")
    suspend fun searchMeal(
        @Query("s") mealName:String
    ): Response<MealsResponse>
}


