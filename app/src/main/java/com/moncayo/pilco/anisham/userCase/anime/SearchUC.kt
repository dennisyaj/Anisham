package com.moncayo.pilco.anisham.userCase.anime

import android.util.Log
import com.moncayo.pilco.anisham.model.endPoints.SearchEndPoint
import com.moncayo.pilco.anisham.model.entities.api.anime.SearchResponse
import com.moncayo.pilco.anisham.model.entities.api.monosChinos.AnimeMCResponse
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
            val service = APIRepository().buildTraceMoeService(SearchEndPoint::class.java)
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

    suspend fun getAnimeWithImg(file: File): SearchResponse? {
        var data: SearchResponse? = null
        try {
            val headerMap = mutableMapOf<String, String>()
            headerMap["accept"] = "application/json"
            val fileReqBody = RequestBody.create(MediaType.parse("application/octet-stream"), file)
            //val image = MultipartBody.Part.createFormData("image", file.name, fileReqBody)
            val service = APIRepository().buildTraceMoeService(SearchEndPoint::class.java)
            val response = service.conImg(headerMap, fileReqBody)
            if (response.isSuccessful) {
                data = response.body()!!
            } else {
                throw Exception("Fracaso en la conexion")
            }
        } catch (e: Exception) {
            Log.d("error", e.message.toString())
        }
        return data
    }

    suspend fun saveAnime(item: AnimeMCResponse) {
        val conn = Anisham.getConn()
        val dao = conn.getHistorialDAO()
        val itemDB = HistorialDB(3, item.image.toString())
        dao.insertAnime(itemDB)
        Log.d(
            "UCE",
            dao.getAllAnimes().toString()
        )
    }

}