package com.example.mealzapp.meals.presentation.main

import com.example.mealzapp.meals.core.NetworkError
import com.example.mealzapp.meals.data.local.Category

data class CategoryState(
    val categories: List<Category>,
    val isLoading:Boolean,
    val error: NetworkError? = null
)
