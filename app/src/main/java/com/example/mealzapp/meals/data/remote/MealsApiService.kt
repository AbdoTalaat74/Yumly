package com.example.mealzapp.meals.data.remote

import retrofit2.http.GET

interface MealsApiService {

    @GET("api/json/v1/1/categories.php")
    suspend fun getCategories(): CategoryResponse

}