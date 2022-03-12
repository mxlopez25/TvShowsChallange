package com.mxlopez.tvserieschallenge.services

import com.mxlopez.tvserieschallenge.models.SearchedShow
import com.mxlopez.tvserieschallenge.models.Show
import com.mxlopez.tvserieschallenge.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvMazeService {
    @GET(Constants.SHOWS_URL)
    suspend fun getShowsPerPage(@Query("page") page: Int = 0): Response<MutableList<Show>>

    @GET(Constants.SEARCH_SHOWS_URL)
    suspend fun searchShowsByName(@Query("q") name: String = ""): Response<MutableList<SearchedShow>>
}