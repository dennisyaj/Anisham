package com.moncayo.pilco.anisham.model.entities.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.moncayo.pilco.anisham.model.entities.api.anime.Title
@Entity()
class AniListDB (
        val id: Int,
    @PrimaryKey(autoGenerate= false)
    val idMal: Int,
    val isAdult: Boolean,
    @ColumnInfo("his_titulo")
    val title: String
)