package com.example.mealzapp.meals.presentation.mealsScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealzapp.meals.data.local.Meal
import com.example.mealzapp.meals.domain.GetMealsByAreaUseCase
import com.example.mealzapp.meals.domain.GetMealsByCategoryUseCase
import com.example.mealzapp.meals.domain.GetMealsByIngredientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMealsByCategoryUseCase: GetMealsByCategoryUseCase,
    private val getMealsByAreaUseCase: GetMealsByAreaUseCase,
    private val getMealsByIngredientUseCase: GetMealsByIngredientUseCase
) : ViewModel() {
    private var filterKey: String
    private var filterType:String
    private var _mealsState by mutableStateOf(
        MealsState(
            meals = emptyList(),
            isLoading = false,
        )
    )
    val mealsState: State<MealsState>
        get() = derivedStateOf { _mealsState }
    private var currentPage = 0
    private var endReached = false
    private val pageSize = 8
    private var filterKeyArgument: String? = savedStateHandle.get<String>("filter_key")
    private var filterTypeArgument: String? = savedStateHandle.get<String>("search_type")
    var topAppBarTitle:String? = null

    init {
        filterKey = filterKeyArgument ?: ""
        filterType = filterTypeArgument?:""
        filterMealsByType(filterType,filterKey)

    }


    private fun getMealsByCategoryName(categoryName: String) {
        if (_mealsState.isLoading || endReached) return
        _mealsState = _mealsState.copy(
            isLoading = true
        )
        viewModelScope.launch(Dispatchers.IO) {
            val newMeals = getMealsByCategoryUseCase.getMealsByCategory(
                categoryName,
                currentPage * pageSize,
                pageSize
            )
            updateMealStateWithNewMeals(newMeals)
        }
    }
    private fun getMealsByAreaName(areaName: String) {
        if (_mealsState.isLoading || endReached) return
        _mealsState = _mealsState.copy(
            isLoading = true
        )
        viewModelScope.launch(Dispatchers.IO) {
            val newMeals = getMealsByAreaUseCase.getMealsByArea(
                areaName,
                currentPage * pageSize,
                pageSize
            )
            updateMealStateWithNewMeals(newMeals)
        }
    }
    private fun getMealsByIngredient(ingredient: String) {
        if (_mealsState.isLoading || endReached) return
        _mealsState = _mealsState.copy(
            isLoading = true
        )
        viewModelScope.launch(Dispatchers.IO) {
            val newMeals = getMealsByIngredientUseCase.getMealsByIngredient(
                ingredient,
                currentPage * pageSize,
                pageSize
            )
            updateMealStateWithNewMeals(newMeals)
        }
    }
    private fun updateMealStateWithNewMeals(newMeals: List<Meal>) {
        if (newMeals.isNotEmpty()) {
            _mealsState = _mealsState.copy(
                meals = _mealsState.meals + newMeals,
                isLoading = false
            )
            currentPage++
        } else {
            endReached = true
        }
        _mealsState = _mealsState.copy(
            isLoading = false
        )
    }
    fun loadMeals() {
        filterMealsByType(filterType,filterKey)
    }
    private fun filterMealsByType(filterType: String, filterKey: String) {
        when (filterType) {
            "category" -> {
                getMealsByCategoryName(filterKey)
                topAppBarTitle = "$filterKeyArgument Category"
            }
            "area" ->{
                getMealsByAreaName(filterKey)
                topAppBarTitle = "$filterKeyArgument Meals"
            }
            "ingredient" -> {
                getMealsByIngredient(filterKey)
                topAppBarTitle = "Meals containing $filterKeyArgument"
            }
        }
    }
}