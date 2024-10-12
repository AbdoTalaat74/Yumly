package com.example.mealzapp.meals.presentation.mealsScreen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealzapp.meals.domain.GetMealsByCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMealsByCategoryUseCase: GetMealsByCategoryUseCase
) : ViewModel(){


    private var categoryName:String

    private var _mealsState by mutableStateOf(
        MealState(
            meals = emptyList(),
            isLoading = false,
        )
    )
    val mealsState: State<MealState>
        get() = derivedStateOf { _mealsState }

    var isLoadingPage = false
    private var currentPage = 0
    private var endReached = false
    private val pageSize = 8


    init {

        val passedArgument: String? = savedStateHandle.get<String>("category_name")

        categoryName = passedArgument ?: ""

        Log.e("MealsViewModelArgument",categoryName)
        getCategoryByName()


    }

    fun getCategoryByName() {

        if (_mealsState.isLoading || endReached) return
        _mealsState.isLoading = true

        viewModelScope.launch(Dispatchers.IO) {
            val newMeals = getMealsByCategoryUseCase.getMealsByCategory(categoryName,currentPage * pageSize, pageSize)
            delay(1000)
            if (newMeals.isNotEmpty()){
                _mealsState = _mealsState.copy(
                    meals = mealsState.value.meals + newMeals,
                    isLoading = false
                )
                currentPage++
            }else{
                endReached = true
            }



            Log.e("MealsViewModelGetCategoryByName", _mealsState.meals.toString())

        }
    }


}