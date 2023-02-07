package com.moncayo.pilco.anisham.model.endPoints

import com.moncayo.pilco.anisham.model.entities.api.anime.SearchResponse
import com.moncayo.pilco.anisham.model.entities.api.monosChinos.AnimeMCResponse
import com.moncayo.pilco.anisham.model.entities.api.monosChinos.SearchMCResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MonosChinosEndPoint {
    @GET("/search/{id}")
    suspend fun searchAnimeByID(
        @Path("id") id: String
    ): Response<SearchMCResponse>

    @GET("/anime/{id}")
    suspend fun getAnimeByID(
        @Path("id") id: String
    ): Response<AnimeMCResponse>
}