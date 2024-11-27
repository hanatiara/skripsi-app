package com.aurea.batikcam.ui.gallery

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.aurea.batikcam.data.AppDatabase
import com.aurea.batikcam.data.dao.GalleryDAO
import com.aurea.batikcam.data.model.Gallery

class GalleryViewModel(application: Application) : AndroidViewModel(application) {
    private val galleryDao: GalleryDAO = AppDatabase.getInstance(application).galleryDao()

    // LiveData to hold gallery data
    val galleryList: LiveData<List<Gallery>> = liveData {
        val data = galleryDao.getAll()  // Get all galleries from the database
        emit(data)  // Emit data to LiveData
    }
}