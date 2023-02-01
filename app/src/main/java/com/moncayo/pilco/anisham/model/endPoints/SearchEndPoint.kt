package com.moncayo.pilco.anisham.model.endPoints

import com.moncayo.pilco.anisham.model.entities.api.anime.SearchResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query


interface SearchEndPoint {
    @GET("search")
    suspend fun searchByURL(
        @Query("anilistInfo") anilistInfo: String,
        @Query("url") url: String
    ): Response<SearchResponse>

    @Multipart
    @POST("search")
    fun searchWithFile(
        @Query("anilistInfo") anilistInfo: String,
        @Part image: MultipartBody.Part
    ): Response<SearchResponse>
}