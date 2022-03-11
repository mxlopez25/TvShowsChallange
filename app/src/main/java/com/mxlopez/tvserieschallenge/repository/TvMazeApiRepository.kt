package com.mxlopez.tvserieschallenge.repository

import com.mxlopez.tvserieschallenge.models.Show
import com.mxlopez.tvserieschallenge.services.RetrofitInstance
import retrofit2.Response

class TvMazeApiRepository {
    suspend fun getShowsPerPage(page: Int = 0): Response<List<Show>> {
        return RetrofitInstance.api.getShowsPerPage(page)
    }
}