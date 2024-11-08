package com.example.mealzapp.meals.presentation.meal_details

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.mealzapp.R
import com.example.mealzapp.composables.AreaCard
import com.example.mealzapp.composables.InfoRow
import com.example.mealzapp.composables.MealIngredient
import com.example.mealzapp.composables.MoreSectionItem
import com.example.mealzapp.meals.data.local.Meal
import com.example.mealzapp.meals.data.local.getIngredientsList
import com.example.mealzapp.ui.theme.Orange
import com.example.mealzapp.ui.theme.dimens
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MealScreen(
    mealState: MealState,
    onCategoryClick: (String) -> Unit,
    onAreaClick: (String) -> Unit,
    onIngredientClick: (String) -> Unit,
    onImageClick: (String) -> Unit,
    onNavigateUpClick: () -> Unit
) {
    val pageState = rememberPagerState()
    val scope = rememberCoroutineScope()
    val tabTitles = listOf("Ingredients", "Instructions", "More")

    val configuration = LocalConfiguration.current
    val screenHeightDp = configuration.screenHeightDp.dp
    val imageHeight = (screenHeightDp * 0.3f)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(mealState.meal?.strMeal ?: "Meals App") },
                navigationIcon = {
                    IconButton(onClick = { onNavigateUpClick() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = MaterialTheme.dimens.small2)
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Image(
                    painter = rememberAsyncImagePainter(mealState.meal?.strMealThumb),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = MaterialTheme.dimens.small1)
                        .height(imageHeight)
                        .clickable {
                            mealState.meal?.strMealThumb?.let { onImageClick(it) }
                        },
                    contentScale = ContentScale.Crop,
                )
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = MaterialTheme.dimens.small1)
                ) {
                    mealState.meal?.strCategory?.let { categoryName ->
                        InfoRow(
                            iconRes = painterResource(R.drawable.category),
                            text = categoryName,
                            onClick = { onCategoryClick(categoryName) },
                            categoryName = categoryName
                        )
                    }

                    val mealArea = mealState.meal?.strArea ?: "Unknown"
                    AreaCard(area = mealArea) {
                        onAreaClick(it)
                    }
                }
            }
            item {
                TabRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = MaterialTheme.dimens.medium1,
                            vertical = MaterialTheme.dimens.small1
                        ),
                    selectedTabIndex = pageState.currentPage,
                    containerColor = Color.Unspecified,
                    contentColor = Orange
                ) {
                    tabTitles.forEachIndexed { index, title ->
                        Tab(
                            selectedContentColor = Orange,
                            unselectedContentColor = Color.Gray,
                            text = {
                                Text(
                                    style = MaterialTheme.typography.titleMedium,
                                    text = title
                                )
                            },
                            selected = pageState.currentPage == index,
                            onClick = {
                                scope.launch {
                                    pageState.scrollToPage(index)
                                }
                            }
                        )
                    }
                }
            }

            item {
                HorizontalPager(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    count = tabTitles.size,
                    state = pageState,
                ) { page ->
                    when (page) {
                        0 -> mealState.meal?.let { meal ->
                            IngredientsSection(meal, onIngredientClick = { ingredient ->
                                onIngredientClick(ingredient)
                            })
                        }

                        1 -> mealState.meal?.strInstructions?.let { InstructionsSection(it) }
                        2 -> mealState.meal?.let {
                            MoreSection(
                                it.strSource ?: "https://www.google.com",
                                it.strYoutube ?: "https://www.youtube.com/"
                            )
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun IngredientsSection(meal: Meal, onIngredientClick: (String) -> Unit) {
    val ingredients = meal.getIngredientsList()
    if (ingredients.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .adaptiveHeight(0.7f)
                .padding(top = MaterialTheme.dimens.small1)
                .padding(horizontal = MaterialTheme.dimens.small1)
        ) {
            items(ingredients.size) { index ->
                MealIngredient(
                    ingredient = ingredients[index].first,
                    measure = ingredients[index].second,
                    onClick = {
                        onIngredientClick(it)
                    }
                )
            }
        }
    }
}

@Composable
fun InstructionsSection(instructions: String) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .adaptiveHeight(0.7f)
            .padding(top = MaterialTheme.dimens.small1),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        item {
            Text(
                modifier = Modifier.padding(
                    horizontal = MaterialTheme.dimens.medium1,
                    vertical = MaterialTheme.dimens.small1
                ),
                text = instructions,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}
@Composable
fun MoreSection(resourceLink: String, youtubeLink: String) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .adaptiveHeight(0.7f)
            .padding(MaterialTheme.dimens.medium1),
        verticalArrangement = Arrangement.Top,
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(resourceLink))
                        context.startActivity(intent)
                    },
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                MoreSectionItem(
                    text = "Resource",
                    iconRes = R.drawable.baseline_insert_link_24
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.small2))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink))
                        context.startActivity(intent)
                    },
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                MoreSectionItem(
                    text = "YouTube Link",
                    iconRes = R.drawable.baseline_youtube_icon
                )
            }
        }
    }
}


@SuppressLint("ModifierFactoryUnreferencedReceiver")
@Composable
fun Modifier.adaptiveHeight(fraction: Float): Modifier {
    val configuration = LocalConfiguration.current
    val screenHeightDp = configuration.screenHeightDp.dp
    val adaptiveHeight = (screenHeightDp * fraction)

    return Modifier.height(adaptiveHeight)
}