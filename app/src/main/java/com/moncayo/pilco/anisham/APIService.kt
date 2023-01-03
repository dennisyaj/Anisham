package com.moncayo.pilco.anisham

import com.moncayo.pilco.anisham.modelo.DogsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun byBreeds(@Url url:String):Response<DogsResponse>
}
