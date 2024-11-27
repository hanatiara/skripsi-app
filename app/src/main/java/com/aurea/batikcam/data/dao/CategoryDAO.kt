package com.aurea.batikcam.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.aurea.batikcam.data.model.Category
@Dao
interface CategoryDAO {

    @Query("SELECT * FROM category")
    fun getAll(): List<Category>

    @Insert
    fun insertAll(vararg categories: Category)
    @Insert
    fun insertList(category: List<Category>)
    @Query("SELECT * FROM category WHERE idCategory IN (:idCategory)")
    fun loadAllByIds(idCategory: IntArray): List<Category>

    // Get batik list from category

    @Delete
    fun delete(category: Category)
}