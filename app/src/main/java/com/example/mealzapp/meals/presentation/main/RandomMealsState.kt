package com.example.mealzapp.meals.presentation.main

import com.example.mealzapp.meals.data.local.Meal

data class RandomMealsState(
    val meals : List<Meal>,
    var isLoading : Boolean,
    val error: String? = null,
    val refreshState: Boolean
)
