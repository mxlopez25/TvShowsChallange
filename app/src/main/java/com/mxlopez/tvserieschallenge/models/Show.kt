package com.mxlopez.tvserieschallenge.models

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
    val premiered: String? = null

)
