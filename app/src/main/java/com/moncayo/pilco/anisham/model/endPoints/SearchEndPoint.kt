package com.moncayo.pilco.anisham.model.endPoints

import com.moncayo.pilco.anisham.model.entities.api.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchEndPoint {
    @GET("/search")
    suspend fun searchByURL(
        @Query("anilistInfo") anilistInfo: String,
        @Query("url") url: String
    ): Response<SearchResponse>
}