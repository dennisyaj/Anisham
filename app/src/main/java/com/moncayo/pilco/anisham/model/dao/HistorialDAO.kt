package com.moncayo.pilco.anisham.model.dao

import androidx.room.*
import com.moncayo.pilco.anisham.model.entities.database.HistorialDB

@Dao
interface HistorialDAO {

    @Query("select * from AniListDB")
    fun getAllAnimes(): List<HistorialDB>

    @Query("select * from AniListDB where id = idAnime ")
    fun getOneAnimne(idAnime: Int): HistorialDB

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnime(a: HistorialDB)

    @Delete
    fun deleteAnime(a: HistorialDB)
}