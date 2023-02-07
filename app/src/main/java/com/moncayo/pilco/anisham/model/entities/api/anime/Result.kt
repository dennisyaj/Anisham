package com.moncayo.pilco.anisham.model.entities.api.anime

import com.moncayo.pilco.anisham.model.entities.api.anime.Anilist

data class Result(
    val anilist: Anilist,
    val episode: Number?,
    val filename: String,
    val from: Double,
    val image: String,
    val similarity: Double,
    val to: Double,
    val video: String
)