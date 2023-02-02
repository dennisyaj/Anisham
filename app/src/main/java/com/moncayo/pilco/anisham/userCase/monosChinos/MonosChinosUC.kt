package com.moncayo.pilco.anisham.userCase.monosChinos

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.moncayo.pilco.anisham.model.endPoints.MonosChinosEndPoint
import com.moncayo.pilco.anisham.model.endPoints.UserEndPoint
import com.moncayo.pilco.anisham.model.entities.api.anime.Result
import com.moncayo.pilco.anisham.model.entities.api.monosChinos.AnimeMCResponse
import com.moncayo.pilco.anisham.model.entities.api.monosChinos.SearchMCResponse
import com.moncayo.pilco.anisham.model.entities.api.user.User
import com.moncayo.pilco.anisham.model.repositories.APIRepository
import kotlinx.coroutines.launch

class MonosChinosUC {
    suspend private fun searchAnimeByID(id: String): SearchMCResponse? {
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

    suspend private fun getAnimeByID(id: String): AnimeMCResponse? {
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

    suspend fun generarDetalles(item: Result): AnimeMCResponse? {
        var tmp: SearchMCResponse? = null
        var tmp2: AnimeMCResponse? = null
        tmp = MonosChinosUC().searchAnimeByID(item.anilist.title?.romaji.toString())
        tmp2 = MonosChinosUC().getAnimeByID(tmp?.get(0)?.id.toString())
        return tmp2
    }
}