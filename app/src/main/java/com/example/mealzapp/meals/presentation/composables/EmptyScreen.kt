package com.example.mealzapp.meals.presentation.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mealzapp.R
import com.example.mealzapp.meals.core.NetworkError


@Composable
fun EmptyScreen(
    error: NetworkError? = null,
    isSearchError:Boolean = false
) {

    val message by remember {
        mutableStateOf(parseErrorMessage(error = error))
    }

    val icon by remember {
        mutableIntStateOf(R.drawable.ic_network_error)
    }

//    if (error == null){
//        if (isSearchError){
//            message = "No Articles found!"
//            icon = R.drawable.ic_search_document
//        }else{
//            message = "You have not saved news so far !"
//            icon = R.drawable.ic_search_document
//        }
//    }

    var startAnimation by remember {
        mutableStateOf(false)
    }

    val alphaAnimation by animateFloatAsState(
        targetValue = if (startAnimation) 0.3f else 0f,
        animationSpec = tween(durationMillis = 1500)
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
    }

    EmptyContent(alphaAnim = alphaAnimation, message = message, iconId = icon)

}

@Composable
fun EmptyContent(alphaAnim: Float, message: String, iconId: Int) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = null,
                tint = if (isSystemInDarkTheme()) LightGray else Black,
                modifier = Modifier
                    .size(120.dp)
                    .alpha(alphaAnim)
            )
            Text(
                modifier = Modifier
                    .padding(10.dp)
                    .alpha(alphaAnim),
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = if (isSystemInDarkTheme()) LightGray else Black,
                textAlign = TextAlign.Center
            )
        }
    }

}


fun parseErrorMessage(error: NetworkError?): String {
    return if (error != null){
        when (error) {
            NetworkError.REQUEST_TIMEOUT -> "The request timed out. Please check your internet connection and try again."
            NetworkError.TOO_MANY_REQUESTS -> "You’re making requests too quickly. Please wait a moment and try again."
            NetworkError.SERVER_ERROR -> "We’re experiencing technical issues. Please try again later."
            NetworkError.SERIALIZATION -> "We’re having trouble processing the data. Please try again later."
            NetworkError.UNKNOWN_ERROR -> "An unexpected error occurred. Please try again."
            NetworkError.NO_INTERNET_CONNECTION -> "No internet connection. Please check your internet connection and try again."
        }
    }else{
        "Unknown Error"
    }
}
