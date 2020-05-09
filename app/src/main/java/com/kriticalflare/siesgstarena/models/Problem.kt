package com.kriticalflare.siesgstarena.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Problem(
    @field:Json(name = "code") val code: String,
    @field:Json(name = "contestCode") val contestCode: String,
    @field:Json(name = "_id") val id: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "points") val points: Int,
    @field:Json(name = "tags") val tags: List<String>
)