package com.example.mealzapp.meals.presentation.mealsScreen

import com.example.mealzapp.meals.data.local.Meal

data class MealsState(
    val meals : List<Meal>,
    var isLoading : Boolean,
    val error: String? = null
)
