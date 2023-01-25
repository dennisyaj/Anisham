package com.moncayo.pilco.anisham.model.repositories

import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Request.Builder
import okhttp3.RequestBody
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SearchResponseApiRepository {

    fun getRetrofit(): Retrofit {
         return Retrofit.Builder()
            .baseUrl("https://api.trace.moe")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}