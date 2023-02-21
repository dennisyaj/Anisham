package com.moncayo.pilco.anisham.userCase.historial

import android.util.Log
import com.moncayo.pilco.anisham.model.entities.api.anime.Result
import com.moncayo.pilco.anisham.model.entities.api.monosChinos.AnimeMCResponse
import com.moncayo.pilco.anisham.model.entities.database.HistorialDB
import com.moncayo.pilco.anisham.utils.Anisham

class HistorialUC {

    suspend fun saveAnime(item: Result) {
        val conn = Anisham.getConn()
        val dao = conn.getHistorialDAO()
        val itemDB = HistorialDB(item.anilist!!.idMal!!.toInt(), item)

        Log.i("UCE",itemDB.toString())
        Log.i("UCE",item.toString())
        dao.insertarHistorial(itemDB)
    }

    suspend fun obtenerHistorial(): List<HistorialDB> {
        val conn = Anisham.getConn()
        val dao = conn.getHistorialDAO()
        return dao.obtenerTodoHistorial()
    }

    suspend fun borrar(numero: Int) {
        val conn = Anisham.getConn()
        val dao = conn.getHistorialDAO()
        Log.i("UCE",dao.getOneAnimne(numero).toString())
    }
}