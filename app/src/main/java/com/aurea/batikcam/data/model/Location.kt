package com.aurea.batikcam.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity (primaryKeys = ["idBatik", "idCategory"])
data class Location(
    @ColumnInfo(name = "idBatik") val idBatik: String,
    @ColumnInfo(name = "idCategory") val idCategory: String
) {
}