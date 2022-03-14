package com.mxlopez.tvserieschallenge.models

import com.google.gson.annotations.SerializedName

data class Show(
    val id: Int,
    val url: String? = null,
    val name: String? = null,
    val type: String? = null,
    val language: String? = null,
    val genres: Array<String>? = null,
    val status: String? = null,
    val runtime: Int,
    val averageRuntime: Int,
    val premiered: String? = null,
    val ended: String? = null,
    @SerializedName("image")
    val image: ImageModel,
    val summary: String? = null,
    var seasons: List<Season>? = null

)
