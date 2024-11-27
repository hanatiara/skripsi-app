package com.aurea.batikcam.ui.category

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.aurea.batikcam.data.AppDatabase
import com.aurea.batikcam.data.dao.CategoryDAO
import com.aurea.batikcam.data.model.Category

class CategoryViewModel (application: Application) : AndroidViewModel(application){
    private val categoryDao: CategoryDAO = AppDatabase.getInstance(application).categoryDao()

    val categoryList: LiveData<List<Category>> = liveData {
        val data = categoryDao.getAll()
        emit(data)
    }
}