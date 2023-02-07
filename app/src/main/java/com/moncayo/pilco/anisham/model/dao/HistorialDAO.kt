package com.moncayo.pilco.anisham.model.dao

import androidx.room.*
import com.moncayo.pilco.anisham.model.entities.database.HistorialDB

@Dao
interface HistorialDAO {

    @Query("select * from HistorialDB")
    fun getAllAnimes(): List<HistorialDB>

    @Query("select * from HistorialDB where idMal = :idAnime ")
    fun getOneAnimne(idAnime: Int): HistorialDB

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnime(a: HistorialDB)

    @Delete
    fun deleteAnime(a: HistorialDB)
}