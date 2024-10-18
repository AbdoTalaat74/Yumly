package com.example.mealzapp.utils

import com.example.mealzapp.R

fun getFlagDrawable(area: String): Int {
    return when (area) {
        "American" -> R.drawable.flag_american
        "British" -> R.drawable.flag_british
        "Canadian" -> R.drawable.flag_canadian
        "Chinese" -> R.drawable.flag_chinese
        "Croatian" -> R.drawable.flag_croatian
        "Dutch" -> R.drawable.flag_dutch
        "Egyptian" -> R.drawable.flag_egyptian
        "Filipino" -> R.drawable.flag_filipino
        "French" -> R.drawable.flag_french
        "Greek" -> R.drawable.flag_greek
        "Indian" -> R.drawable.flag_indian
        "Irish" -> R.drawable.flag_irish
        "Italian" -> R.drawable.flag_italian
        "Jamaican" -> R.drawable.flag_jamaican
        "Japanese" -> R.drawable.flag_japanese
        "Kenyan" -> R.drawable.flag_kenyan
        "Malaysian" -> R.drawable.flag_malaysian
        "Mexican" -> R.drawable.flag_mexican
        "Moroccan" -> R.drawable.flag_moroccan
        "Polish" -> R.drawable.flag_polish
        "Portuguese" -> R.drawable.flag_portuguese
        "Russian" -> R.drawable.flag_russian
        "Spanish" -> R.drawable.flag_spanish
        "Thai" -> R.drawable.flag_thai
        "Tunisian" -> R.drawable.flag_tunisian
        "Turkish" -> R.drawable.flag_turkish
        "Ukrainian" -> R.drawable.flag_ukrainian
        "Vietnamese" -> R.drawable.flag_vietnamese
        else -> R.drawable.flag_unknown
    }
}