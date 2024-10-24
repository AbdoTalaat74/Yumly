package com.example.mealzapp.meals.presentation.meal_details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.mealzapp.R
import com.example.mealzapp.composables.AreaCard
import com.example.mealzapp.composables.InfoRow
import com.example.mealzapp.composables.MealIngredient
import com.example.mealzapp.composables.MoreSectionItem
import com.example.mealzapp.meals.data.local.Meal
import com.example.mealzapp.meals.data.local.getIngredientsList
import com.example.mealzapp.ui.theme.Orange
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
    onNavigateUpClick:()-> Unit
) {
    val pageState = rememberPagerState()
    val scope = rememberCoroutineScope()
    val tabTitles = listOf("Ingredients", "Instructions", "More")

    Scaffold(modifier = Modifier.fillMaxSize(), topBar =
    {
        TopAppBar(
            title = { Text(mealState.meal?.strMeal?:"Meals App") },
            navigationIcon = {
                IconButton(onClick = {onNavigateUpClick()}) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 4.dp)
        )
    }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                )
        ) {
            Image(
                painter = rememberAsyncImagePainter(mealState.meal?.strMealThumb),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.25f)
                    .clickable {
                        mealState.meal?.strMealThumb?.let { onImageClick(it) }
                    },
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
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

            Spacer(modifier = Modifier.height(24.dp))
            ScrollableTabRow(
                selectedTabIndex = pageState.currentPage,
                containerColor = Color.Unspecified,
                contentColor = Orange
            ) {
                tabTitles.forEachIndexed { index, title ->

                    Tab(
                        selectedContentColor = Orange,
                        unselectedContentColor = Color.Gray,
                        modifier = Modifier.fillMaxWidth(),
                        text = {
                            Text(
                                fontSize = 18.sp,
                                modifier = Modifier.fillMaxWidth(),
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

@Composable
fun IngredientsSection(meal: Meal, onIngredientClick: (String) -> Unit) {
    val ingredients = meal.getIngredientsList()
    if (ingredients.isNotEmpty()) {
        LazyColumn(modifier = Modifier.padding(horizontal = 8.dp)) {
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
            .padding(top = 8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        item {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                text = instructions, style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium

                )
            )
        }
    }
}

@Composable
fun MoreSection(resourceLink: String, youtubeLink: String) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        MoreSectionItem(
            modifier = Modifier.clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(resourceLink))
                context.startActivity(intent)
            }, text = "Resource",
            iconRes = R.drawable.baseline_insert_link_24
        )

        Spacer(modifier = Modifier.height(16.dp))

        MoreSectionItem(
            modifier = Modifier.clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink))
                context.startActivity(intent)
            },
            text = "Youtube Link",
            iconRes = R.drawable.basline_youtub_icon,
        )

    }
}





