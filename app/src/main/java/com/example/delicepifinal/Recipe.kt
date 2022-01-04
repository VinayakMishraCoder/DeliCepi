package com.example.delicepifinal

import androidx.lifecycle.MutableLiveData

data class Recipe(

    val image: String, // imp
    val ingredientLines: List<String>, // imp
    val label: String, // imp
    val mealType: List<String>, // imp
    val shareAs: String, // imp - pref over url
    val url: String // imp

)