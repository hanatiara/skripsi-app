package com.aurea.batikcam.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.aurea.batikcam.data.model.Batik
import com.aurea.batikcam.data.model.Category
import com.aurea.batikcam.data.model.Location

@Dao
interface CategoryDAO {

    @Query("SELECT * FROM category")
    fun getAll(): List<Category>

    @Insert
    fun insertAll(vararg categories: Category)
    @Insert
    fun insertList(category: List<Category>)

    @Query("SELECT * FROM category WHERE idCategory IN (:idCategory) LIMIT 1")
    fun loadAllByIds(idCategory: String): Category?

//  Get batik list from category
    @Query("SELECT * FROM batik WHERE idBatik IN (SELECT idBatik FROM location WHERE idCategory = :idCategory )")
    fun getIdBatikFrom(idCategory: String): List<Batik>

    @Delete
    fun delete(category: Category)
}