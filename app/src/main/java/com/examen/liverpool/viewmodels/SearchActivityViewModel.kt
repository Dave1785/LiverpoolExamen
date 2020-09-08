package com.examen.liverpool.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.examen.liverpool.data.Refinement
import com.examen.liverpool.data.RefinementGroups
import com.examen.liverpool.data.RequestDTO
import com.examen.liverpool.data.ResponseSearch
import com.examen.liverpool.repository.LiverpoolRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SearchActivityViewModel( private val liverpoolRepository: LiverpoolRepository?) :
    ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    //Response Search
    private var _ResponseSearch = MutableLiveData<ResponseSearch>()
    val ResponseSearch: LiveData<ResponseSearch>
        get() = _ResponseSearch

      fun getProducts(requestDTO: RequestDTO){
        coroutineScope.launch {
            _ResponseSearch.value=liverpoolRepository?.getProducts(requestDTO)
        }
    }


}