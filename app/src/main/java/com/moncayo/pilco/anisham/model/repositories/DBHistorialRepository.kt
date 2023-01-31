package com.moncayo.pilco.anisham.model.repositories

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.moncayo.pilco.anisham.model.entities.database.AniListDB

@Database(
    entities = [AniListDB::class],
    version = 1,
    exportSchema = false
)
abstract class DBHistorialRepository : RoomDatabase() {

    abstract fun getHistorialDAO(): AniListDB
}
class DBHistorialConexion(){
    fun getConnection(context:Context){
        Room.databaseBuilder(context,DBHistorialRepository::class.java,"DBAnisham")
    }
}