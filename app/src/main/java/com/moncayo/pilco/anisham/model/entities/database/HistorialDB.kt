package com.moncayo.pilco.anisham.model.entities.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class HistorialDB(
    @PrimaryKey(autoGenerate = false)
    val idMal: Int,
    @ColumnInfo("titulo")
    val title: String
)