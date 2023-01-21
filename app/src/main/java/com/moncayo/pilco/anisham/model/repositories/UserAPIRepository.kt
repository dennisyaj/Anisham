package com.moncayo.pilco.anisham.model.repositories

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserAPIRepository {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://gorest.co.in/public/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}