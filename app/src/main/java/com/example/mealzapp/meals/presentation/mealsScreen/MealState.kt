package com.example.mealzapp.meals.presentation.mealsScreen

import com.example.mealzapp.meals.data.local.Meal
import java.lang.Error

data class MealState(
    val meals : List<Meal>,
    var isLoading : Boolean,
    val error: String? = null
)
