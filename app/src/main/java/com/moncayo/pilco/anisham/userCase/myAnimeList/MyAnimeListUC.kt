package com.moncayo.pilco.anisham.userCase.myAnimeList

import android.util.Log
import com.moncayo.pilco.anisham.model.endPoints.MyAnimeListEndPoint
import com.moncayo.pilco.anisham.model.endPoints.SearchEndPoint
import com.moncayo.pilco.anisham.model.entities.api.myAnimeList.ResponseMyAnimeList
import com.moncayo.pilco.anisham.model.entities.api.user.User
import com.moncayo.pilco.anisham.model.repositories.APIRepository

class MyAnimeListUC {

    suspend fun obtenerAnime(id:String):ResponseMyAnimeList?{
        var data: ResponseMyAnimeList? = null
        try {
             val service = APIRepository().buildMyAnimeList(MyAnimeListEndPoint::class.java)
            val response = service.searchAnimeByID(id,"title,synopsis,mean,genres,rating")
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