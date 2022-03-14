package com.mxlopez.tvserieschallenge.models

data class Episode(
    val name: String? = null,
    val number: Int,
    val season: Int,
    val summary: String? = null,
    val image: String? = null
)
