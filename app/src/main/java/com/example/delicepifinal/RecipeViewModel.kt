package com.example.delicepifinal

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

class RecipeViewModel : ViewModel() {

    var allRecipe : MutableLiveData<Response<Hits>> = MutableLiveData()

    lateinit var query : String

    init {
        Log.d("chez", "initialized ")
    }

    fun getRecipes(){

        val KEY = "242d12f113e25625c0448f50515044e2"
        val ID = "8907dc9b"
        if (query.length == 0) return
        viewModelScope.launch {
            val apiResult = RetroFitHelper.apiService.getRecipe(query, ID, KEY)
            allRecipe.value = apiResult
        }
    }

}