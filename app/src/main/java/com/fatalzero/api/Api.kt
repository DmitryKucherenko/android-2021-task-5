package com.fatalzero

import com.fatalzero.model.Cat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface Api {

    @GET("/v1/images/search?limit=2&page=1&order=Desc")
    suspend fun getBooks():List<Cat>
}

object ApiDateImpl {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://api.thecatapi.com")
        .build()

    private val booksService = retrofit.create(Api::class.java)


    suspend fun getBooks(): List<Cat?> {
        return withContext(Dispatchers.IO) {
            booksService.getBooks().map{
                cat ->  Cat(cat.id,cat.url,cat.height,cat.width)
            }
        }

    }
}