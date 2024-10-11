package com.example.mealzapp.meals.presentation.mealsScreen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private var _state by mutableStateOf("") // Initial value is an empty string
    val state: String
        get() = _state // Exposing the state as State<String>
    init {
        val passedArgument: String? = savedStateHandle.get<String>("category_name")

        // Update the state with the retrieved argument if it's not null
        _state = passedArgument ?: "Default value" //

        Log.e("MealsViewModelArgument",state)
    }
}