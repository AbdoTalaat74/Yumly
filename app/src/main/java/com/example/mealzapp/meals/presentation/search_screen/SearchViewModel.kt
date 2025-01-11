package com.example.mealzapp.meals.presentation.search_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealzapp.meals.core.NetworkError
import com.example.mealzapp.meals.domain.SearchMealUseCase
import com.example.mealzapp.meals.presentation.mealsScreen.MealsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMealUseCase: SearchMealUseCase
) : ViewModel() {

    private var _mealsState by mutableStateOf(
        MealsState(
            meals = emptyList(),
            isLoading = false
        )
    )
    val mealsState: State<MealsState>
        get() = derivedStateOf { _mealsState }

    private val _query = mutableStateOf("")
    val query: State<String> = _query

    fun updateQuery(newQuery: String) {
        _query.value = newQuery
    }

    fun searchMeal(query:String){
        _mealsState = _mealsState.copy(
            isLoading = true
        )
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val meals = searchMealUseCase.searchMeal(query)
                if (meals.isNotEmpty()){
                    _mealsState = _mealsState.copy(
                        meals = meals,
                        isLoading = false,
                        error = null
                    )
                }
            }catch (_:Exception){
                _mealsState = _mealsState.copy(
                    meals = emptyList(),
                    isLoading = false,
                    error = NetworkError.UNKNOWN_ERROR
                )
            }

        }
    }

    fun stopSearch(){
        _mealsState = _mealsState.copy(
            meals = emptyList(),
            isLoading = false,
            error = null
        )
    }
}