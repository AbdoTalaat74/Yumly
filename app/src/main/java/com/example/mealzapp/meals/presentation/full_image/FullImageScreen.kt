package com.example.mealzapp.meals.presentation.full_image


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.mealzapp.meals.core.NetworkError
import com.example.mealzapp.meals.presentation.composables.EmptyScreen
import me.saket.telephoto.zoomable.rememberZoomableState
import me.saket.telephoto.zoomable.zoomable


@Composable
fun FullImageScreen(imageUrl: String) {
    val zoomState = rememberZoomableState()
    val viewModel:FullImageViewModel = hiltViewModel()
    val connectionState = viewModel.connectionState.collectAsState()
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (connectionState.value){
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .zoomable(zoomState),

                contentScale = ContentScale.FillWidth,
                alignment = Alignment.Center

            )
        }else{
            EmptyScreen(error = NetworkError.NO_INTERNET_CONNECTION)
        }

    }

}
