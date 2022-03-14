package com.mxlopez.tvserieschallenge.repository

import com.mxlopez.tvserieschallenge.models.Episode
import com.mxlopez.tvserieschallenge.models.SearchedShow
import com.mxlopez.tvserieschallenge.models.Season
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

    suspend fun getShowSeasons(showId: String = "1"): Response<MutableList<Season>> {
        return RetrofitInstance.api.getShowSeasons(showId)
    }

    suspend fun getShowEpisodes(showId: String = "1"): Response<MutableList<Episode>> {
        return RetrofitInstance.api.getShowSeasonsEpisode(showId)
    }
}