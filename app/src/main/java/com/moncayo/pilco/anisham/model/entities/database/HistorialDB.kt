package com.moncayo.pilco.anisham.model.entities.database

import androidx.room.*
import com.google.gson.Gson
import com.moncayo.pilco.anisham.model.entities.api.anime.Anilist
import com.moncayo.pilco.anisham.model.entities.api.anime.Result


@Entity
@TypeConverters(AnilistConverter::class)
class HistorialDB(
    @PrimaryKey(autoGenerate = false)
    val idMal: Int,
    val itemHistorial: Result,
    ) {
    override fun toString(): String {
        return "HistorialDB(id=$idMal)"
    }
}

class AnilistConverter {

    @TypeConverter
    fun fromAnilist(r: Result): String {
        return Gson().toJson(r)
    }

    @TypeConverter
    fun toAnilist(resultString: String): Result {
        return Gson().fromJson(resultString, Result::class.java)
    }
}
