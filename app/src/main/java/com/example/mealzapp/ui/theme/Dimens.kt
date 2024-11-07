package com.example.mealzapp.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
data class Dimens(
    val extraSmall: Dp = 4.dp,
    val small1: Dp = 8.dp,
    val small2: Dp = 12.dp,
    val small3: Dp = 16.dp,
    val medium1: Dp = 24.dp,
    val medium2: Dp = 32.dp,
    val medium3: Dp = 40.dp,
    val large: Dp = 48.dp,
    val buttonHeight: Dp = 40.dp,
    val logoSize: Dp = 42.dp,
    val imageSize: Dp = 150.dp,
    val cardWidth: Dp = 200.dp,
    val smallCardSize: Dp = 100.dp,
    val extraLarge:Dp = 64.dp // New value for CompactMediumDimens

)

val CompactSmallDimens = Dimens(
    small1 = 4.dp,
    small2 = 6.dp,
    small3 = 8.dp,
    medium1 = 12.dp,
    medium2 = 16.dp,
    medium3 = 20.dp,
    large = 40.dp,
    buttonHeight = 28.dp,
    logoSize = 32.dp,
    imageSize = 120.dp,
    cardWidth = 160.dp,
    smallCardSize = 80.dp,
    extraLarge = 48.dp
)

val CompactMediumDimens = Dimens(
    small1 = 6.dp,
    small2 = 10.dp,
    small3 = 14.dp,
    medium1 = 20.dp,
    medium2 = 24.dp,
    medium3 = 28.dp,
    large = 50.dp,
    buttonHeight = 32.dp,
    logoSize = 36.dp,
    imageSize = 150.dp,
    cardWidth = 200.dp,
    smallCardSize = 100.dp,
    extraLarge = 64.dp
)

val CompactDimens = Dimens(
    small1 = 8.dp,
    small2 = 12.dp,
    small3 = 16.dp,
    medium1 = 24.dp,
    medium2 = 28.dp,
    medium3 = 32.dp,
    large = 60.dp,
    buttonHeight = 36.dp,
    logoSize = 40.dp,
    imageSize = 180.dp,
    cardWidth = 240.dp,
    smallCardSize = 120.dp,
    extraLarge = 72.dp
)

val MediumDimens = Dimens(
    small1 = 10.dp,
    small2 = 14.dp,
    small3 = 18.dp,
    medium1 = 28.dp,
    medium2 = 32.dp,
    medium3 = 36.dp,
    large = 70.dp,
    buttonHeight = 40.dp,
    logoSize = 48.dp,
    imageSize = 200.dp,
    cardWidth = 260.dp,
    smallCardSize = 140.dp,
    extraLarge = 80.dp
)

val ExpandedDimens = Dimens(
    small1 = 12.dp,
    small2 = 16.dp,
    small3 = 20.dp,
    medium1 = 32.dp,
    medium2 = 40.dp,
    medium3 = 48.dp,
    large = 90.dp,
    buttonHeight = 44.dp,
    logoSize = 56.dp,
    imageSize = 240.dp,
    cardWidth = 300.dp,
    smallCardSize = 160.dp,
    extraLarge = 100.dp
)
