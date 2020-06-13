package com.kriticalflare.siesgstarena.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SubmissionStats(
    @field:Json(name = "accepted")
    val accepted: Int,

    @field:Json(name = "wrongAnswer")
    val wrongAnswer: Int,

    @field:Json(name = "compilationError")
    val compilationError: Int,

    @field:Json(name = "runtimeError")
    val runtimeError: Int,

    @field:Json(name = "timeLimitExceeded")
    val timeLimitExceeded: Int
)
