package com.moncayo.pilco.anisham.modelo

import com.google.gson.annotations.SerializedName

data class RecomendResponse(
    val animes: Animes
)

data class Animes(
    val anime: List<Anime>,
    val status: Boolean
)

data class Anime(
    val name: String,
    val score: String
)