package com.example.mealzapp.meals.presentation.full_image


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import coil.compose.rememberAsyncImagePainter

@Composable
fun FullImageScreen(imageUrl: String) {
    var scale by remember { mutableFloatStateOf(1f) }
    var minScale by remember { mutableFloatStateOf(1f) }  // Minimum scale (fits the screen)
    val maxScale by remember { mutableFloatStateOf(2f) }  // Maximum scale for zoom

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        scale = if (scale > minScale) minScale else maxScale
                    }
                )
            }
            .pointerInput(Unit) {
                detectTransformGestures { _, _, zoom, _ ->
                    scale = (scale * zoom).coerceAtLeast(minScale)  // Limit zoom-out
                }
            }
    ) {
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .onGloballyPositioned { layoutCoordinates ->
                    val screenWidth = layoutCoordinates.size.width.toFloat()
                    minScale = 1f.coerceAtLeast(screenWidth / layoutCoordinates.size.width)
                    scale = minScale
                }
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale
                ),
            contentScale = ContentScale.Fit
        )
    }
}
