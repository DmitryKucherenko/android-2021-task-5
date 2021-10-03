package com.fatalzero.api

import com.fatalzero.model.Cat
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("/v1/images/search")
    suspend fun getCats(@Query("limit") size: Int, @Query("page") page: Int): List<Cat>
}

object ApiDateImpl {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://api.thecatapi.com")
        .build()
    val catService: Api = retrofit.create(Api::class.java)
}
