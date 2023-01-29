package com.moncayo.pilco.anisham.model.entities.api

data class Anilist(
    val id: Int,
    val idMal: Int,
    val isAdult: Boolean,
    val synonyms: List<String>,
    val title: Title
)