package com.moncayo.pilco.anisham.model.entities.database

import com.moncayo.pilco.anisham.model.entities.api.anime.Anilist

class HistorialDB(
    val anilist: Anilist,
    val filename: String,
    val from: Double,
    val image: String,
    val similarity: Double,
    val to: Double,
    val video: String
)