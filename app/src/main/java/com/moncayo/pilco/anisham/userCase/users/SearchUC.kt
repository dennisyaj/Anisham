package com.moncayo.pilco.anisham.userCase.users

import android.util.Log
import com.moncayo.pilco.anisham.model.endPoints.SearchEndPoint
import com.moncayo.pilco.anisham.model.entities.api.SearchResponse
import com.moncayo.pilco.anisham.model.repositories.SearchResponseApiRepository
import okhttp3.MediaType
import okhttp3.RequestBody
import java.io.File


class SearchUC {
    suspend fun getAnime(selectedImageUrl: String): SearchResponse? {
        var data: SearchResponse? = null
        try {
            val service = SearchResponseApiRepository().getRetrofit()
            val response = service.create(SearchEndPoint::class.java).searchByURL(selectedImageUrl)
            Log.i("list","aqui")
            if (response.isSuccessful) {
                data = response.body()!!
            } else {
                throw Exception("Fracaso en la conexion")
            }
        } catch (e: Exception) {
            Log.d("Error", e.message.toString())
        }
        return data
    }
}