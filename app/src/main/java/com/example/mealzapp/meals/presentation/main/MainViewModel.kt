package com.example.mealzapp.meals.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealzapp.meals.core.NetworkError
import com.example.mealzapp.meals.data.local.Meal
import com.example.mealzapp.meals.domain.GetCategoriesUseCase
import com.example.mealzapp.meals.domain.GetCountriesUseCase
import com.example.mealzapp.meals.domain.GetIngredientsUseCase
import com.example.mealzapp.meals.domain.GetRandomMealUseCase
import com.example.mealzapp.meals.domain.util.NetworkHelper
import com.example.mealzapp.meals.domain.util.Result
import com.example.mealzapp.meals.presentation.mealsScreen.MealsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getRandomMealUseCase: GetRandomMealUseCase,
    private val getIngredientsUseCase: GetIngredientsUseCase,
    private val getCountriesUseCase: GetCountriesUseCase,
    private val networkHelper: NetworkHelper

    ) : ViewModel() {
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    private val _categoryState = MutableStateFlow(
        CategoryState(
            isLoading = false,
            categories = emptyList(),
            error = null
        )
    )
    val categoryState: StateFlow<CategoryState> = _categoryState

    private var _randomMealsState = MutableStateFlow(
        RandomMealsState(
            isLoading = true,
            meals = emptyList(),
            refreshState = false,
            error = null

        )
    )
    val randomMealsState: StateFlow<RandomMealsState> = _randomMealsState

    private val randomMeal = MutableStateFlow(
        RandomMealState(
            isLoading = false,
            error = null,
            refreshState = false
        )
    )


    private var _ingredientsState = MutableStateFlow(
        MealsState(
            meals = emptyList(),
            isLoading = false,
            error = null

        )
    )

    val ingredientsState: StateFlow<MealsState> = _ingredientsState

    private var _countriesState = MutableStateFlow(
        MealsState(
            meals = emptyList(),
            isLoading = false,
            error = null

        )
    )
    val countriesState: StateFlow<MealsState> = _countriesState

    private val _connectionState = MutableStateFlow(false)

    val connectionState:StateFlow<Boolean> = _connectionState

    init {
        _connectionState.value = true
        if (networkHelper.isNetworkAvailable()){
            getCategories()
            getRandomMeals()
            getIngredients()
            getCountries()
        }else{
            _connectionState.value = false
            Log.e("networkHelperLog","No Internet")
        }


    }

    private fun getCategories() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            try {
                val result = getCategoriesUseCase()
                _categoryState.value = when (result) {
                    is Result.Success -> {
                        _categoryState.value.copy(
                            categories = result.data.categories,
                            isLoading = false,
                            error = null
                        )
                    }

                    is Result.Error -> {
                        _categoryState.value.copy(
                            isLoading = false,
                            categories = emptyList(),
                            error = result.error
                        )
                    }
                }
            } catch (e: Exception) {
                _categoryState.value = _categoryState.value.copy(
                    isLoading = false,
                    categories = emptyList(),
                    error = NetworkError.UNKNOWN_ERROR
                )
            }
        }
    }


    private fun getRandomMeals() {
        viewModelScope.launch {
            _randomMealsState.value =
                _randomMealsState.value.copy(refreshState = true)
            var meals = mutableListOf<Meal>()
            for (i in 1..5) {
                getRandomMeal().meal?.let {
                    meals = (meals + it).toMutableList()
                }
            }
            if (meals.isNotEmpty()) {
                _randomMealsState.value = _randomMealsState.value.copy(
                    isLoading = false,
                    meals = meals,
                    refreshState = false
                )
            }
        }
    }

    private suspend fun getRandomMeal(): RandomMealState {
        try {
            val result = getRandomMealUseCase()
            randomMeal.value = when (result) {
                is Result.Error -> {
                    randomMeal.value.copy(
                        isLoading = false,
                        error = result.error
                    )
                }

                is Result.Success -> {
                    randomMeal.value.copy(
                        isLoading = false,
                        error = null,
                        meal = result.data.meal
                    )
                }
            }
        } catch (e: Exception) {
            _categoryState.value = _categoryState.value.copy(
                isLoading = false,
                categories = emptyList(),
                error = NetworkError.UNKNOWN_ERROR
            )
        }
        Log.e("RandomMealLog", randomMeal.value.toString())
        return randomMeal.value
    }

    fun refreshRandomMeals() {
        if (networkHelper.isNetworkAvailable()){
            try {
                getRandomMeals()

            }catch (e:Exception){
                _connectionState.value = true
            }

        }else{
            _connectionState.value = false
        }
    }

    private fun getIngredients() {
        _ingredientsState.value = _ingredientsState.value.copy(
            isLoading = true
        )
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            try {
                val result = getIngredientsUseCase.getIngredients()
                _ingredientsState.value = when (result) {
                    is Result.Error -> {
                        _ingredientsState.value.copy(
                            isLoading = false,
                            error = result.error
                        )
                    }

                    is Result.Success -> {
                        _ingredientsState.value.copy(
                            isLoading = false,
                            meals = result.data.meals
                        )
                    }
                }
            } catch (e: Exception) {
                _categoryState.value = _categoryState.value.copy(
                    isLoading = false,
                    categories = emptyList(),
                    error = NetworkError.UNKNOWN_ERROR
                )
            }
        }
    }

    private fun getCountries() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            try {
                val result = getCountriesUseCase.getCountries()
                _countriesState.value = when (result) {
                    is Result.Error -> {
                        _countriesState.value.copy(
                            isLoading = false,
                            error = result.error
                        )
                    }

                    is Result.Success -> {
                        _countriesState.value.copy(
                            isLoading = false,
                            meals = result.data.meals
                        )
                    }
                }
            }catch (e: Exception) {
                _countriesState.value = _countriesState.value.copy(
                    isLoading = false,
                    meals = emptyList(),
                    error = NetworkError.UNKNOWN_ERROR
                )
            }
        }
    }


}
