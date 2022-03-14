package com.mxlopez.tvserieschallenge.models

import com.google.gson.annotations.SerializedName

data class Episode(
    val name: String? = null,
    val number: Int,
    val season: Int,
    val summary: String? = null,
    @SerializedName("image")
    val image: ImageModel?
)
