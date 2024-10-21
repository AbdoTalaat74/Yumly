package com.example.mealzapp.meals.presentation.full_image

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FullImageViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel(){
    var imageUrl: String? = Uri.decode(savedStateHandle.get<String>("image_url"))
}