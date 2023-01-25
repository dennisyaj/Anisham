package com.moncayo.pilco.anisham.model.entities.api

data class SearchResponse(
    val error: String,
    val frameCount: Int,
    val result: List<Result>
)