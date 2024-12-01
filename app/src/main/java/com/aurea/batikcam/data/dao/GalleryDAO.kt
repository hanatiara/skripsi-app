package com.aurea.batikcam.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.aurea.batikcam.data.model.Gallery

@Dao
interface GalleryDAO {

    @Query("SELECT * FROM gallery")
    fun getAll(): List<Gallery>

    @Insert
    fun insertAll(vararg galleries: Gallery) // Accepts individual Gallery objects.

    @Insert
    fun insertList(galleries: List<Gallery>) // Accepts a list of Gallery objects.

    @Query("SELECT * FROM gallery WHERE idGallery IN (:idGallery)")
    fun loadAllByIds(idGallery: String): Gallery? // Retrieves specific entries by their IDs.

    @Delete
    fun delete(gallery: Gallery) // Deletes a specific Gallery entry.
}