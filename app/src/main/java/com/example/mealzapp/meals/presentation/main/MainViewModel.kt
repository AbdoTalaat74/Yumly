package com.example.mealzapp.meals.presentation.main

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealzapp.meals.data.local.Meal
import com.example.mealzapp.meals.domain.GetCategoriesUseCase
import com.example.mealzapp.meals.domain.GetRandomMealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getRandomMealUseCase: GetRandomMealUseCase
) : ViewModel() {

    private var _state by mutableStateOf(
        CategoryState(
            categories = emptyList(), isLoading = true
        )
    )

    val state: State<CategoryState>
        get() = derivedStateOf { _state }

    private var _randomMealsState by mutableStateOf(
        RandomMealsState(
            isLoading = true,
            meals = emptyList(),
            refreshState = false
        )
    )
    val randomMealsState: State<RandomMealsState>
        get() = derivedStateOf { _randomMealsState }


    init {
        getCategories()
        getRandomMeals()
    }


    private fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            val receivedCategories = getCategoriesUseCase()
            _state = _state.copy(
                categories = receivedCategories,
                isLoading = false
            )
        }

    }

    private fun getRandomMeals() {
        viewModelScope.launch {
            _randomMealsState = _randomMealsState.copy(refreshState = true) // Start refreshing
            var meals = mutableListOf<Meal>()
            for (i in 1..5) {
                meals = (meals + getRandomMealUseCase()).toMutableList()
            }
            Log.e("MainViewModelGetRandomMeals", meals.toString())
            if (meals.isNotEmpty()) {
                _randomMealsState = _randomMealsState.copy(
                    isLoading = false,
                    meals = meals,
                    refreshState = false
                )
            }
        }
    }

    fun refreshRandomMeals() {
        getRandomMeals()
    }
}