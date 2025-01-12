package com.example.mealzapp.meals.presentation.meal_details

import com.example.mealzapp.meals.core.NetworkError
import com.example.mealzapp.meals.data.local.Meal

data class MealState(
    var meal: Meal?,
    var isLoading: Boolean,
    val error: NetworkError? = null
)
