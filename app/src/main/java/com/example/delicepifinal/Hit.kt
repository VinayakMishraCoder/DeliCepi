package com.example.delicepifinal

import androidx.lifecycle.LiveData
import androidx.versionedparcelable.ParcelField
import kotlinx.android.parcel.Parcelize
import java.io.Serializable


data class Hit(
    val recipe: Recipe // imp
) : Serializable