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
import com.example.mealzapp.meals.domain.GetCountriesUseCase
import com.example.mealzapp.meals.domain.GetIngredientsUseCase
import com.example.mealzapp.meals.domain.GetRandomMealUseCase
import com.example.mealzapp.meals.presentation.mealsScreen.MealsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getRandomMealUseCase: GetRandomMealUseCase,
    private val getIngredientsUseCase: GetIngredientsUseCase,
    private val getCountriesUseCase: GetCountriesUseCase
) : ViewModel() {


    private var _categoryState by mutableStateOf(
        CategoryState(
            categories = emptyList(), isLoading = true
        )
    )

    val categoryState: State<CategoryState>
        get() = derivedStateOf { _categoryState }

    private var _randomMealsState by mutableStateOf(
        RandomMealsState(
            isLoading = true,
            meals = emptyList(),
            refreshState = false
        )
    )
    val randomMealsState: State<RandomMealsState>
        get() = derivedStateOf { _randomMealsState }


    private var _ingredientsState by mutableStateOf(
        MealsState(
            meals = emptyList(),
            isLoading = false
        )
    )

    val ingredientsState: State<MealsState>
        get() = derivedStateOf { _ingredientsState }


    private var _countriesState by mutableStateOf(
        MealsState(
            meals = emptyList(),
            isLoading = false
        )
    )
    val countriesState: State<MealsState>
        get() = derivedStateOf { _countriesState }

    init {
        getCategories()
        getRandomMeals()
        getIngredients()
        getCountries()
    }


    private fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            val receivedCategories = getCategoriesUseCase()
            _categoryState = _categoryState.copy(
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


    private fun getIngredients() {
        _ingredientsState = _ingredientsState.copy(
            isLoading = true
        )
        viewModelScope.launch(Dispatchers.IO) {
            val ingredients = getIngredientsUseCase.getIngredients()
            if (ingredients.isNotEmpty()) {
                _ingredientsState = _ingredientsState.copy(
                    meals = _ingredientsState.meals + ingredients,
                    isLoading = false
                )
            }
        }
    }

    private fun getCountries() {
        _countriesState = _countriesState.copy(
            isLoading = true
        )
        viewModelScope.launch(Dispatchers.IO) {
            val countries = getCountriesUseCase.getCountries()
            if (countries.isNotEmpty()) {
                _countriesState = _countriesState.copy(
                    meals = _countriesState.meals + countries,
                    isLoading = false
                )
            }
        }
    }
}