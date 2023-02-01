package com.moncayo.pilco.anisham.userCase.anime

import android.util.Log
import com.moncayo.pilco.anisham.model.endPoints.SearchEndPoint
import com.moncayo.pilco.anisham.model.entities.api.anime.Anilist
import com.moncayo.pilco.anisham.model.entities.api.anime.SearchResponse
import com.moncayo.pilco.anisham.model.entities.database.HistorialDB
import com.moncayo.pilco.anisham.model.repositories.APIRepository
import com.moncayo.pilco.anisham.utils.Anisham
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class SearchUC {
    suspend fun getAnime(selectedImageUrl: String): SearchResponse? {
        var data: SearchResponse? = null
        try {
            val service = APIRepository().buildSearchService(SearchEndPoint::class.java)
            val response = service.searchByURL("", selectedImageUrl)
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

    suspend fun getAnimeWithImg(pathToImage: String): SearchResponse? {
        var data: SearchResponse? = null
        try {
            val file = File(pathToImage)
            val fileReqBody = RequestBody.create(MediaType.parse("application/octet-stream"), file)
            val image = MultipartBody.Part.createFormData("image", file.name, fileReqBody)
            val service = APIRepository().buildSearchService(SearchEndPoint::class.java)
            val response = service.searchWithFile("",image)
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

    suspend fun saveAnime(item :Anilist){
        val conn = Anisham.getConn()
        val dao = conn!!.getHistorialDAO()

        val anime = HistorialDB(0,"")
        conn!!.getHistorialDAO().insertAnime(anime)
    }
}