package com.aurea.batikcam.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.aurea.batikcam.data.model.Batik
import com.aurea.batikcam.data.model.Category

@Dao
interface BatikDAO {
    @Query("SELECT * FROM batik")
    fun getAll(): List<Batik>

    @Insert
    fun insertAll(vararg batik: Batik)
    @Insert
    fun insertList(batik: List<Batik>)
    @Query("SELECT * FROM batik WHERE idBatik IN (:idBatik) LIMIT 1")
    fun loadAllByIds(idBatik: String): Batik?

    // Get batik list from category
    @Delete
    fun delete(batik: Batik)
}