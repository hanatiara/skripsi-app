package com.aurea.batikcam.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.aurea.batikcam.data.model.Category
import com.aurea.batikcam.data.model.History

@Dao
interface HistoryDAO {
    @Query("SELECT * FROM history")
    fun getAll(): List<HistoryDAO>

    @Insert
    fun insertAll(vararg history: History)
    @Insert
    fun insertList(history: List<History>)
    @Query("SELECT * FROM history WHERE idHistory IN (:idHistory)")
    fun loadAllByIds(idHistory: IntArray): List<History>
    @Delete
    fun delete(history: History)
}