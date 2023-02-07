package com.moncayo.pilco.anisham.model.repositories

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.moncayo.pilco.anisham.model.dao.HistorialDAO
import com.moncayo.pilco.anisham.model.entities.database.HistorialDB

@Database(
    entities = [HistorialDB::class],
    version = 1,
    exportSchema = false
)
abstract class DBHistorialRepository : RoomDatabase() {
    abstract fun getHistorialDAO(): HistorialDAO
}

class DBHistorialConexion(){
    fun getConnection(context:Context) =  Room.databaseBuilder(
        context,
        DBHistorialRepository::class.java,
        "DBAnisham")
        .build()

}