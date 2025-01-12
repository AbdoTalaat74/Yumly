package com.example.mealzapp.meals.presentation.mealsScreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealzapp.meals.core.NetworkError
import com.example.mealzapp.meals.data.local.Meal
import com.example.mealzapp.meals.domain.GetMealsByAreaUseCase
import com.example.mealzapp.meals.domain.GetMealsByCategoryUseCase
import com.example.mealzapp.meals.domain.GetMealsByIngredientUseCase
import com.example.mealzapp.meals.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
    private var _mealsState = MutableStateFlow(
        MealsState(
            meals = emptyList(),
            isLoading = false,
        )
    )
    val mealsState: StateFlow<MealsState> = _mealsState
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
        if (_mealsState.value.isLoading || endReached) return
        _mealsState.value = _mealsState.value.copy(
            isLoading = true
        )
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val result = getMealsByCategoryUseCase.getMealsByCategory(
                    categoryName,
                    currentPage * pageSize,
                    pageSize
                )
                when(result){
                    is Result.Error -> {
                        _mealsState.value = _mealsState.value.copy(
                            isLoading = false,
                            error = result.error
                        )
                    }
                    is Result.Success -> {
                        updateMealStateWithNewMeals(result.data)

                    }
                }
            }catch (e:Exception){
                _mealsState.value = _mealsState.value.copy(
                    isLoading = false,
                    error = NetworkError.UNKNOWN_ERROR
                )
            }


        }
    }
    private fun getMealsByAreaName(areaName: String) {
        if (_mealsState.value.isLoading || endReached) return
        _mealsState.value = _mealsState.value.copy(
            isLoading = true
        )
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = getMealsByAreaUseCase.getMealsByArea(
                    areaName,
                    currentPage * pageSize,
                    pageSize
                )
                when(result){
                    is Result.Error -> {
                        _mealsState.value = _mealsState.value.copy(
                            isLoading = false,
                            error = result.error
                        )
                    }
                    is Result.Success -> {
                        updateMealStateWithNewMeals(result.data)

                    }
                }
            }catch (e:Exception){
                _mealsState.value = _mealsState.value.copy(
                    isLoading = false,
                    error = NetworkError.UNKNOWN_ERROR
                )
            }
        }
    }
    private fun getMealsByIngredient(ingredient: String) {
        if (_mealsState.value.isLoading || endReached) return
        _mealsState.value = _mealsState.value.copy(
            isLoading = true
        )
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = getMealsByIngredientUseCase.getMealsByIngredient(
                    ingredient,
                    currentPage * pageSize,
                    pageSize
                )
                when(result){
                    is Result.Error -> {
                        _mealsState.value = _mealsState.value.copy(
                            isLoading = false,
                            error = result.error
                        )
                    }
                    is Result.Success -> {
                        updateMealStateWithNewMeals(result.data)

                    }
                }
            }catch (e:Exception){
                _mealsState.value = _mealsState.value.copy(
                    isLoading = false,
                    error = NetworkError.UNKNOWN_ERROR
                )
            }
        }
    }
    private fun updateMealStateWithNewMeals(newMeals: List<Meal>) {
        if (newMeals.isNotEmpty()) {
            _mealsState.value = _mealsState.value.copy(
                meals = _mealsState.value.meals + newMeals,
                isLoading = false
            )
            currentPage++
        } else {
            endReached = true
        }
        _mealsState.value = _mealsState.value.copy(
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