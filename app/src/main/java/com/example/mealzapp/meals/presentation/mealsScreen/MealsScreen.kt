package com.example.mealzapp.meals.presentation.mealsScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MealsScreen(modifier: Modifier = Modifier) {
    val mealsVM : MealsViewModel = hiltViewModel()
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize()){
        Toast.makeText(context,mealsVM.state,Toast.LENGTH_LONG).show()
    }
}