package com.mxlopez.tvserieschallenge.models

data class Season(
    val id: Int,
    val episodeOrder: Int,
    val number: Int,
    val image: ImageModel,
    var episodes: List<Episode>? = null
)