package com.mxlopez.tvserieschallenge.repository

import com.mxlopez.tvserieschallenge.models.SearchedShow
import com.mxlopez.tvserieschallenge.models.Show
import com.mxlopez.tvserieschallenge.services.RetrofitInstance
import retrofit2.Response

class TvMazeApiRepository {
    suspend fun getShowsPerPage(page: Int = 0): Response<MutableList<Show>> {
        return RetrofitInstance.api.getShowsPerPage(page)
    }

    suspend fun getShowsByName(name: String = ""): Response<MutableList<SearchedShow>> {
        return RetrofitInstance.api.searchShowsByName(name)
    }
}