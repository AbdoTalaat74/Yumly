package com.example.mealzapp.meals.presentation.meal_details


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealzapp.meals.data.local.Meal
import com.example.mealzapp.meals.domain.GetMealDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMealDetailsUseCase: GetMealDetailsUseCase
) : ViewModel() {

    private var mealId: Int
    private var _mealState by mutableStateOf(
        MealState(
            meal = Meal(),
            isLoading = true,
        )
    )
    val mealState: State<MealState> = derivedStateOf { _mealState }


    init {
        val passedArgument: Int? = savedStateHandle.get<Int>("meal_id")
        mealId = passedArgument ?: 0

        Log.e("MealDetailsViewMealId", mealId.toString())

        if (mealId == 0) {
            Log.e("MealDetailsViewModelGetMeal", "No Data Fetched, id is wrong")
        } else {
            getMealDetails(mealId)
        }


    }


    private fun getMealDetails(mealId: Int) {

        viewModelScope.launch(Dispatchers.Main) {
            val meal = getMealDetailsUseCase.getMealDetails(mealId).meals.firstOrNull()
            if (meal != null) {
                _mealState = _mealState.copy(
                    meal = meal,
                    isLoading = false
                )
                Log.e("MealDetailsViewModelGetMeal", "Meal Fetched Successfully: \n $meal.")
            } else {
                Log.e("MealDetailsViewModelGetMeal", "Error.")
            }
            Log.e("MealDetailsViewModelGetMeal", "Meal Fetched Successfully: \n $meal.")


        }

    }


}
