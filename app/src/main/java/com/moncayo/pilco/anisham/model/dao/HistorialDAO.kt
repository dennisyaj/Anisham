package com.moncayo.pilco.anisham.model.dao

import androidx.room.*
import com.moncayo.pilco.anisham.model.entities.api.anime.Anilist
import com.moncayo.pilco.anisham.model.entities.database.AniListDB

@Dao
interface HistorialDAO {

    @Query("select * from AniListDB")
    fun getAllAnimes():List<Anilist>

    @Query("select * from AniListDB where id = idAnime ")
    fun getOneAnimne(idAnime: Int): AniListDB

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnime(a: AniListDB)

    @Delete
    fun deleteAnime(a: AniListDB)
}