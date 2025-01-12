package com.example.mealzapp.meals.presentation.full_image

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.mealzapp.meals.domain.util.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class FullImageViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    networkHelper: NetworkHelper
): ViewModel(){
    private val _connectionState = MutableStateFlow(false)
    val connectionState:StateFlow<Boolean> = _connectionState
    var imageUrl: String? = Uri.decode(savedStateHandle.get<String>("image_url"))

    init {
        _connectionState.value = networkHelper.isNetworkAvailable()
    }
}