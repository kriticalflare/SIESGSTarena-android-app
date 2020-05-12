package com.kriticalflare.siesgstarena.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Error(
    @field:Json(name = "message")
    val message: String,
    @field:Json(name = "details")
    val details: String?
)