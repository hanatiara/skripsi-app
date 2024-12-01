package com.aurea.batikcam.ui.category

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.aurea.batikcam.data.AppDatabase
import com.aurea.batikcam.data.dao.BatikDAO
import com.aurea.batikcam.data.dao.CategoryDAO
import com.aurea.batikcam.data.model.Batik
import com.aurea.batikcam.data.model.Category
import kotlinx.coroutines.launch

class CategoryViewModel (application: Application) : AndroidViewModel(application){
    private val categoryDao: CategoryDAO = AppDatabase.getInstance(application).categoryDao()
    private val batikDao: BatikDAO = AppDatabase.getInstance(application).batikDao()

    val categoryList: LiveData<List<Category>> = liveData {
        val data = categoryDao.getAll()
        emit(data)
    }

    fun getCategoryById(idCategory: String): LiveData<Category?> {
        val categoryLiveData = MutableLiveData<Category?>()
        viewModelScope.launch {
            val category = categoryDao.loadAllByIds(idCategory)
            categoryLiveData.postValue(category)
        }
        return categoryLiveData
    }

    fun getBatikListByCategoryId(categoryId: String): LiveData<List<Batik>> {
        val batikListLiveData = MutableLiveData<List<Batik>>()
        // Fetch data from the database in a background thread
        viewModelScope.launch {
            val data = categoryDao.getIdBatikFrom(categoryId) // Assuming this method exists
            batikListLiveData.postValue(data) // Post the value to LiveData
        }
        return batikListLiveData
    }


    fun getBatikListById(idBatik: String): LiveData<Batik?> {
        val batikList = MutableLiveData<Batik?>()
        viewModelScope.launch {
            val data = batikDao.loadAllByIds(idBatik)
            batikList.postValue(data)
        }
        return batikList
    }
}