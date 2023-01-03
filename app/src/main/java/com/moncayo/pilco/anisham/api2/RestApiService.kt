package com.moncayo.pilco.anisham.api2

import com.moncayo.pilco.anisham.modelo.RecomendResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestApiService {
    fun addUser(userData: RecomendResponse, onResult: (RecomendResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(APIRest::class.java)
        retrofit.addUser(userData).enqueue(
            object : Callback<RecomendResponse> {
                override fun onFailure(call: Call<RecomendResponse>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<RecomendResponse>, response: Response<RecomendResponse>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }
}