package com.aurea.batikcam.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.aurea.batikcam.data.dao.BatikDAO
import com.aurea.batikcam.data.dao.CategoryDAO
import com.aurea.batikcam.data.dao.GalleryDAO
import com.aurea.batikcam.data.dao.LocationDAO
import com.aurea.batikcam.data.data.BatikData
import com.aurea.batikcam.data.data.CategoryData
import com.aurea.batikcam.data.data.GalleryData
import com.aurea.batikcam.data.data.LocationData
import com.aurea.batikcam.data.model.Batik
import com.aurea.batikcam.data.model.Category
import com.aurea.batikcam.data.model.Gallery
import com.aurea.batikcam.data.model.Location

@Database(entities = [Gallery::class, Category::class, Location::class, Batik::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun galleryDao() : GalleryDAO
    abstract fun categoryDao(): CategoryDAO
    abstract fun locationDao(): LocationDAO
    abstract fun batikDao(): BatikDAO

    companion object {

        private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase{
            if (instance == null) {
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "batikcam-db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            Thread {
                                getInstance(context).galleryDao().insertList(GalleryData.galleryList)
                                getInstance(context).categoryDao().insertList(CategoryData.categoryList)
                                getInstance(context).locationDao().insertList(LocationData.locationList)
                                getInstance(context).batikDao().insertList(BatikData.batikList)
                                println("database inserted")
                            }.start()
                        }
                    })
                    .build()
            }
            return instance!!
        }
    }

}