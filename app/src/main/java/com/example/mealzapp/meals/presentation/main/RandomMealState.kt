package com.example.mealzapp.meals.presentation.main

import com.example.mealzapp.meals.core.NetworkError
import com.example.mealzapp.meals.data.local.Meal

data class RandomMealState(
    val meal: List<Meal>?=null,
    var isLoading : Boolean,
    val error: NetworkError? = null,
    val refreshState: Boolean
)
