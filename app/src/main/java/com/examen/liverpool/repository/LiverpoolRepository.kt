package com.examen.liverpool.repository

import com.examen.liverpool.data.RequestDTO
import com.examen.liverpool.data.ResponseSearch
import com.examen.liverpool.network.LiverpoolApi
import com.examen.liverpool.network.LiverpoolApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.lang.Exception

class LiverpoolRepository {


    suspend fun getProducts(requestDTO: RequestDTO):ResponseSearch?{
        var responseSearch:ResponseSearch?=null
        withContext(Dispatchers.IO) {
            val searchRequest= LiverpoolApi.retrofitService.getProductsByName(requestDTO.forcePlp,requestDTO.valueSearch,requestDTO.page,requestDTO.numberOfItems)
            try {
                responseSearch=searchRequest.await()
            } catch (e: Exception) {
                return@withContext null
            }
        }

        return responseSearch
    }

}