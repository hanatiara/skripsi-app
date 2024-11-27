package com.aurea.batikcam.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Gallery(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "idGallery") val idGallery: Int = 0, // Change to Int
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "image") val image: String?,
    @ColumnInfo(name = "idBatik") val idBatik: String
)
