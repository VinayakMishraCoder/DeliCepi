package com.example.delicepifinal

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitHelper {

    val BASE_URL : String = "https://api.edamam.com/"

    val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiService : ApiService by lazy {
        retrofitBuilder.build()
            .create(ApiService::class.java)
    }

}