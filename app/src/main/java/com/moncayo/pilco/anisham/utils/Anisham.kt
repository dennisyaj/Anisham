package com.moncayo.pilco.anisham.utils

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.moncayo.pilco.anisham.model.repositories.DBHistorialConexion
import com.moncayo.pilco.anisham.model.repositories.DBHistorialRepository

class Anisham : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    @SuppressLint("StaticFieldLeak")
    companion object {
        private val dbCon: DBHistorialRepository? = null
        var context: Context? = null
        fun getConn(): DBHistorialRepository? {
            return dbCon ?: return DBHistorialConexion().getConnection(context!!)
        }
    }
}