package com.aurea.batikcam.data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.aurea.batikcam.data.model.Location

@Dao
interface LocationDAO {
    @Insert
    fun insertList(location: List<Location>)
}