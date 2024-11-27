package com.aurea.batikcam.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class History(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "idHistory") val idHistory: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "idBatik") val idBatik: String
) {
}