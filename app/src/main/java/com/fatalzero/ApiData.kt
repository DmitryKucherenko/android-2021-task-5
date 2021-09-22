package com.fatalzero

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiData(
    @Json(name = "cats") val cats:List<Cat>
)


@JsonClass(generateAdapter = true)
class Cat(
    @Json(name = "id") val id:String,
    @Json(name = "url") val url:String,
    @Json(name = "width") val width:String,
    @Json(name = "height") val height:String
)