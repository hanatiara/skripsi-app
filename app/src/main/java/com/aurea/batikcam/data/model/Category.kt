package com.aurea.batikcam.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category (
    @PrimaryKey @ColumnInfo(name = "idCategory") val idCategory: String, // Change to Int
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "image") val image: String?,
)