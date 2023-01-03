package com.moncayo.pilco.anisham.api2

import com.moncayo.pilco.anisham.modelo.RecomendResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIRest {

    @Headers("Content-Type: application/json")
    @POST("recomendacion")
    fun addUser(@Body userData: RecomendResponse): Call<RecomendResponse>
}