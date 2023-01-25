package com.moncayo.pilco.anisham.model.entities.api

data class Result(
    val anilist: Int,
    val episode: Int,
    val filename: String,
    val from: Double,
    val image: String,
    val similarity: Double,
    val to: Double,
    val video: String
)