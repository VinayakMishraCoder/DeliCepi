package com.example.delicepifinal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/search")
    suspend fun getRecipe(

        @Query("q") q : String,
        @Query("app_id") app_id: String,
        @Query("app_key") app_key: String

    ) : Response<Hits>
}