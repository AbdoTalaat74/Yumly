package com.example.mealzapp.meals.presentation.search_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealzapp.meals.core.NetworkError
import com.example.mealzapp.meals.domain.SearchMealUseCase
import com.example.mealzapp.meals.domain.util.Result
import com.example.mealzapp.meals.presentation.mealsScreen.MealsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMealUseCase: SearchMealUseCase
) : ViewModel() {

    private var _mealsState = MutableStateFlow(
        MealsState(
            meals = emptyList(),
            isLoading = false
        )
    )
    val mealsState: StateFlow<MealsState> =  _mealsState

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    fun updateQuery(newQuery: String) {
            _query.value = newQuery
    }

    fun searchMeal(query:String){
        if (query.isNotEmpty() && query.isNotBlank()){
            _mealsState.value = _mealsState.value.copy(
                isLoading = true
            )
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    when(val result = searchMealUseCase.searchMeal(query)){
                            is Result.Error -> {
                                _mealsState.value = _mealsState.value.copy(
                                    isLoading = false,
                                    error = result.error
                                )
                            }
                            is Result.Success -> {
                                _mealsState.value = if (result.data.isNotEmpty()){
                                    _mealsState.value.copy(
                                        meals = result.data,
                                        isLoading = false,
                                        error = null
                                    )
                                }else{
                                    _mealsState.value.copy(
                                        meals = emptyList(),
                                        isLoading = false,
                                        error = NetworkError.NO_RESULTS_FOUND
                                    )
                                }
                            }
                        }
                }
                catch (e: NullPointerException){
                    e.printStackTrace()
                    _mealsState.value = _mealsState.value.copy(
                        meals = emptyList(),
                        isLoading = false,
                        error = NetworkError.NO_RESULTS_FOUND
                    )
                }
                catch (e:Exception){
                    e.printStackTrace()
                    _mealsState.value = _mealsState.value.copy(
                        meals = emptyList(),
                        isLoading = false,
                        error = NetworkError.UNKNOWN_ERROR
                    )
                }

            }
        }

    }

    fun stopSearch(){
        _mealsState.value = _mealsState.value.copy(
            meals = emptyList(),
            isLoading = false,
            error = null
        )
    }
}