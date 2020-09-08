package com.examen.liverpool.network

import com.examen.liverpool.data.ResponseSearch
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://shoppapp.liverpool.com.mx/"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Retrofit Instance
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

/**
 * Interfaz de Cotiza
 */
interface LiverpoolApiService {

  @GET("appclienteservices/services/v3/plp?")
    fun getProductsByName(@Query("force-plp")force:Boolean,@Query("search-string")search:String,@Query("page-number")page:Int,@Query("number-of-items-per-page")number:Int):Deferred<ResponseSearch>

}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object LiverpoolApi {
    val retrofitService : LiverpoolApiService by lazy { retrofit.create(LiverpoolApiService::class.java) }
}