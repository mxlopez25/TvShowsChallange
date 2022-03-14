package com.mxlopez.tvserieschallenge.utils

import com.mxlopez.tvserieschallenge.models.Show

class Constants {
    companion object {
        const val BASE_URL = "https://api.tvmaze.com/"

        const val SHOWS_URL = "shows"
        const val SEARCH_SHOWS_URL = "search/shows"

        var selectedShow: Show? = null
    }
}