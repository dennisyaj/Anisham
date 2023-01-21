package com.moncayo.pilco.anisham.model.endPoints

import com.moncayo.pilco.anisham.model.entities.api.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserEndPoint {
    @GET("users/{numero}")
    suspend fun getUser(@Path("numero") numero: String): Response<User>;
}