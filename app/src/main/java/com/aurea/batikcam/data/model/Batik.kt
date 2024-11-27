package com.aurea.batikcam.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Batik(
    @PrimaryKey @ColumnInfo(name = "idBatik") val idBatik: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val image: String
) {
}