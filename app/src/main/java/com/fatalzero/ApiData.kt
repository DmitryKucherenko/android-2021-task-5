package com.fatalzero

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiData(
    @Json(name = "autor") val autor:String,
    @Json(name = "books") val books:List<Book>
)


@JsonClass(generateAdapter = true)
class Book(@Json(name = "id") val id:Int,
           @Json(name="title") val title:String)