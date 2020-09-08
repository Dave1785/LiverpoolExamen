package com.examen.liverpool.viewmodelfactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.examen.liverpool.repository.LiverpoolRepository
import com.examen.liverpool.viewmodels.SearchActivityViewModel

class SearchActivityViewModelFactory(val context: Context,private val liverpoolRepository: LiverpoolRepository?) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SearchActivityViewModel::class.java)){
            return SearchActivityViewModel(liverpoolRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}