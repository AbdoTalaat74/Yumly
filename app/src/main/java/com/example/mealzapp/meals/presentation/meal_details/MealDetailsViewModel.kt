package com.example.mealzapp.meals.presentation.meal_details


import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealzapp.meals.core.NetworkError
import com.example.mealzapp.meals.data.local.Meal
import com.example.mealzapp.meals.domain.GetMealDetailsUseCase
import com.example.mealzapp.meals.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMealDetailsUseCase: GetMealDetailsUseCase
) : ViewModel() {

    private var mealId: Int
    private var _mealState = MutableStateFlow(
        MealState(
            meal = Meal(),
            isLoading = true,
            error = null
        )
    )
    val mealState: StateFlow<MealState> = _mealState


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
//            val meal = getMealDetailsUseCase.getMealDetails(mealId).meals.firstOrNull()
//            if (meal != null) {
//                _mealState = _mealState.copy(
//                    meal = meal,
//                    isLoading = false
//                )
//                Log.e("MealDetailsViewModelGetMeal", "Meal Fetched Successfully: \n $meal.")
//            } else {
//                Log.e("MealDetailsViewModelGetMeal", "Error.")
//            }
//            Log.e("MealDetailsViewModelGetMeal", "Meal Fetched Successfully: \n $meal.")
//
            try {
                val result = getMealDetailsUseCase.getMealDetails(mealId)
                _mealState.value = when(result){
                    is Result.Error -> {
                        _mealState.value.copy(
                            isLoading = false,
                            error = result.error
                        )
                    }
                    is Result.Success -> {
                        _mealState.value.copy(
                            isLoading = false,
                            error = null,
                            meal = result.data.meals.firstOrNull()
                        )
                    }
                }
            }catch (e:Exception){
                _mealState.value = _mealState.value.copy(
                    isLoading = false,
                    error = NetworkError.UNKNOWN_ERROR
                )
            }

        }

    }


}
