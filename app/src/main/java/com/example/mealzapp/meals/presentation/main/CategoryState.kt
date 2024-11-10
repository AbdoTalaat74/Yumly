package com.example.mealzapp.meals.presentation.main

import com.example.mealzapp.meals.data.local.Category

data class CategoryState(
    val categories: List<Category>,
    val isLoading:Boolean,
    val error: String? = null
)
