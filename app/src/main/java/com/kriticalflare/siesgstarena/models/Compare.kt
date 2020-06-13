package com.kriticalflare.siesgstarena.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Compare(

    @field:Json(name = "user")
    val user: User,

    @field:Json(name = "performace")
    val performance: SubmissionStats
)
