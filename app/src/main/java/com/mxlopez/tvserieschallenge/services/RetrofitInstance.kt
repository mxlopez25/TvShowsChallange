package com.mxlopez.tvserieschallenge.services

import com.mxlopez.tvserieschallenge.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: TvMazeService by lazy {
        retrofit.create(TvMazeService::class.java)
    }
}