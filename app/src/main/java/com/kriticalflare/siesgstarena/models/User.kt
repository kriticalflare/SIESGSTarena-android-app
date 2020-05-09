package com.kriticalflare.siesgstarena.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @field:Json(name="_id") val id: String,
    @field:Json(name="name") val name: String,
    @field:Json(name="username") val username: String,
    @field:Json(name="email") val email: String,
    @field:Json(name="ratings") val ratings: Int?,
    @field:Json(name="about") val about: String?,
    @field:Json(name="codechefLink") val codechefLink: String?,
    @field:Json(name="codeforcesLink") val codeforcesLink: String?,
    @field:Json(name="githubLink") val githubLink: String?
)