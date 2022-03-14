package com.mxlopez.tvserieschallenge.utils

import com.mxlopez.tvserieschallenge.models.Show

class Constants {
    companion object {
        const val EP_NAME = "EP_NAME"
        const val EP_NUMBER = "EP_NUMBER"
        const val EP_SEASON = "EP_SEASON"
        const val EP_IMAGE = "EP_IMAGE"
        const val EP_SUMMARY = "EP_SUMMARY"
        const val BASE_URL = "https://api.tvmaze.com/"

        const val SHOWS_URL = "shows"
        const val SHOW_SEASONS_URL = "shows/{show_id}/seasons"
        const val SHOW_EPISODES_URL = "shows/{show_id}/episodes"
        const val SEARCH_SHOWS_URL = "search/shows"

        var selectedShow: Show? = null
    }
}