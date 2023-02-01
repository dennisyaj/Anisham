package com.moncayo.pilco.anisham.userCase.monosChinos

import android.util.Log
import com.moncayo.pilco.anisham.model.endPoints.MonosChinosEndPoint
import com.moncayo.pilco.anisham.model.endPoints.UserEndPoint
import com.moncayo.pilco.anisham.model.entities.api.monosChinos.AnimeMCResponse
import com.moncayo.pilco.anisham.model.entities.api.monosChinos.SearchMCResponse
import com.moncayo.pilco.anisham.model.entities.api.user.User
import com.moncayo.pilco.anisham.model.repositories.APIRepository

class MonosChinosUC {
    suspend fun searchAnimeByID(id: String): SearchMCResponse? {
        var data: SearchMCResponse? = null
        try {
            val service = APIRepository().buildMonosChinosService(MonosChinosEndPoint::class.java)
            val response = service.searchAnimeByID(id)
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

    suspend fun getAnimeByID(id: String): AnimeMCResponse? {
        var data: AnimeMCResponse? = null
        try {
            val service = APIRepository().buildMonosChinosService(MonosChinosEndPoint::class.java)
            val response = service.getAnimeByID(id)
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