package com.moncayo.pilco.anisham.model.endPoints

import com.moncayo.pilco.anisham.model.entities.api.SearchResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SearchEndPoint {
    @POST("/search")
    suspend  fun search(@Body image: RequestBody): Response<SearchResponse>
}