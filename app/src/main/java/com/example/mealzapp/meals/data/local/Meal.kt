package com.example.mealzapp.meals.data.local

data class Meal(
    val idMeal: String? = null,
    val strMeal: String? = null,
    val strCategory: String? = null,
    val strArea: String? = null,
    val strInstructions: String? = null,
    val strMealThumb: String? = null,
    val strTags: String? = null,
    val strYoutube: String? = null,
    val strIngredient1: String? = null,
    val strIngredient2: String? = null,
    val strIngredient3: String? = null,
    val strIngredient4: String? = null,
    val strIngredient5: String? = null,
    val strIngredient6: String? = null,
    val strIngredient7: String? = null,
    val strIngredient8: String? = null,
    val strIngredient9: String? = null,
    val strIngredient10: String? = null,
    val strIngredient11: String? = null,
    val strIngredient12: String? = null,
    val strIngredient13: String? = null,
    val strIngredient14: String? = null,
    val strIngredient15: String? = null,
    val strIngredient16: String? = null,
    val strIngredient17: String? = null,
    val strIngredient18: String? = null,
    val strIngredient19: String? = null,
    val strIngredient20: String? = null,
    val strMeasure1: String? = null,
    val strMeasure2: String? = null,
    val strMeasure3: String? = null,
    val strMeasure4: String? = null,
    val strMeasure5: String? = null,
    val strMeasure6: String? = null,
    val strMeasure7: String? = null,
    val strMeasure8: String? = null,
    val strMeasure9: String? = null,
    val strMeasure10: String? = null,
    val strMeasure11: String? = null,
    val strMeasure12: String? = null,
    val strMeasure13: String? = null,
    val strMeasure14: String? = null,
    val strMeasure15: String? = null,
    val strMeasure16: String? = null,
    val strMeasure17: String? = null,
    val strMeasure18: String? = null,
    val strMeasure19: String? = null,
    val strMeasure20: String? = null,
    val strSource: String? = null
)



//fun Meal.getIngredientsList(): List<Pair<String, String>> {
//    val result = mutableListOf<Pair<String, String>>()
//
//    if (strIngredient1.isNullOrBlank() || strMeasure1.isNullOrBlank())
//        return result
//    result.add(strIngredient1 to strMeasure1)
//
//    if (strIngredient2.isNullOrBlank() || strMeasure2.isNullOrBlank())
//        return result
//    result.add(strIngredient2 to strMeasure2)
//
//    if (strIngredient3.isNullOrBlank() || strMeasure3.isNullOrBlank())
//        return result
//    result.add(strIngredient3 to strMeasure3)
//
//    if (strIngredient4.isNullOrBlank() || strMeasure4.isNullOrBlank())
//        return result
//    result.add(strIngredient4 to strMeasure4)
//
//    if (strIngredient5.isNullOrBlank() || strMeasure5.isNullOrBlank())
//        return result
//    result.add(strIngredient5 to strMeasure5)
//
//    if (strIngredient6.isNullOrBlank() || strMeasure6.isNullOrBlank())
//        return result
//    result.add(strIngredient6 to strMeasure6)
//
//    if (strIngredient7.isNullOrBlank() || strMeasure7.isNullOrBlank())
//        return result
//    result.add(strIngredient7 to strMeasure7)
//
//    if (strIngredient8.isNullOrBlank() || strMeasure8.isNullOrBlank())
//        return result
//    result.add(strIngredient8 to strMeasure8)
//
//    if (strIngredient9.isNullOrBlank() || strMeasure9.isNullOrBlank())
//        return result
//    result.add(strIngredient9 to strMeasure9)
//
//    if (strIngredient10.isNullOrBlank() || strMeasure10.isNullOrBlank())
//        return result
//    result.add(strIngredient10 to strMeasure10)
//
//    if (strIngredient11.isNullOrBlank() || strMeasure11.isNullOrBlank())
//        return result
//    result.add(strIngredient11 to strMeasure11)
//
//    if (strIngredient12.isNullOrBlank() || strMeasure12.isNullOrBlank())
//        return result
//    result.add(strIngredient12 to strMeasure12)
//
//    if (strIngredient13.isNullOrBlank() || strMeasure13.isNullOrBlank())
//        return result
//    result.add(strIngredient13 to strMeasure13)
//
//    if (strIngredient14.isNullOrBlank() || strMeasure14.isNullOrBlank())
//        return result
//    result.add(strIngredient14 to strMeasure14)
//
//    if (strIngredient15.isNullOrBlank() || strMeasure15.isNullOrBlank())
//        return result
//    result.add(strIngredient15 to strMeasure15)
//
//    if (strIngredient16.isNullOrBlank() || strMeasure16.isNullOrBlank())
//        return result
//    result.add(strIngredient16 to strMeasure16)
//
//    if (strIngredient17.isNullOrBlank() || strMeasure17.isNullOrBlank())
//        return result
//    result.add(strIngredient17 to strMeasure17)
//
//    if (strIngredient18.isNullOrBlank() || strMeasure18.isNullOrBlank())
//        return result
//    result.add(strIngredient18 to strMeasure18)
//
//    if (strIngredient19.isNullOrBlank() || strMeasure19.isNullOrBlank())
//        return result
//    result.add(strIngredient19 to strMeasure19)
//
//    if (strIngredient20.isNullOrBlank() || strMeasure20.isNullOrBlank())
//        return result
//    result.add(strIngredient20 to strMeasure20)
//
//    return result
//}

fun Meal.getIngredientsList(): List<Pair<String, String>> {
    val result = mutableListOf<Pair<String, String>>()

    for (i in 1..20) {
        val ingredientField = this::class.java.getDeclaredField("strIngredient$i")
        ingredientField.isAccessible = true
        val ingredient = ingredientField.get(this) as? String

        val measureField = this::class.java.getDeclaredField("strMeasure$i")
        measureField.isAccessible = true
        val measure = measureField.get(this) as? String

        if (ingredient.isNullOrBlank() || measure.isNullOrBlank()) break
        result.add(ingredient to measure)
    }

    return result
}



