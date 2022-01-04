package com.example.delicepifinal

import android.icu.number.IntegerWidth
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.io.Serializable

data class Hits(

    val q: String,
    val from : Int,
    val more : Boolean,
    val count : Int,
    val hits: List<Hit> // imp

) : Serializable