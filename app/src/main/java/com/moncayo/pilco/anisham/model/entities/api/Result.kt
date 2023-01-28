package com.moncayo.pilco.anisham.model.entities.api

data class Result(
    val anilist: Int = 0,
    val episode: Int = 0,
    val filename: String = "",
    val from: Double = 0.0,
    val image: String = "",
    val similarity: Double = 0.0,
    val to: Double = 0.0,
    val video: String = ""
)