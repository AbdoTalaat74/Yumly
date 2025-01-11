package com.example.mealzapp.meals.data.remote

import com.example.mealzapp.meals.data.local.Meal
import com.google.gson.annotations.SerializedName

data class MealResponse(
    @SerializedName("meals")
    val meal : List<Meal>
)

