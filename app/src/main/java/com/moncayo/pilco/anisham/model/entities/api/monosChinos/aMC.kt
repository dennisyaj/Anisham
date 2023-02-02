package com.moncayo.pilco.anisham.model.entities.api.monosChinos

data class aMC(
    val banner: String,
    val date: String,
    val episodes: List<Episode>,
    val genders: List<String>,
    val image: String,
    val rating: String,
    val sinopsis: String,
    val status: String,
    val title: String,
    val titleAlt: String
)