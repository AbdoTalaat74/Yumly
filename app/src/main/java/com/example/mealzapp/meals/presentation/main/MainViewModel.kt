package com.example.mealzapp.meals.presentation.main

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealzapp.meals.domain.GetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private var _state by mutableStateOf(
        CategoryState(
            categories = emptyList(), isLoading = true
        )
    )

    val state: State<CategoryState>
        get() = derivedStateOf { _state }


    init {
        getMeals()
    }


    private fun getMeals() {
        viewModelScope.launch(Dispatchers.IO) {
            val receivedCategories = getCategoriesUseCase()
            _state = _state.copy(
                categories = receivedCategories,
                isLoading = false
            )
        }

    }


}