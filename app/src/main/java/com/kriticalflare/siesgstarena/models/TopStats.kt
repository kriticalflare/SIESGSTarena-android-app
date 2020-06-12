package com.kriticalflare.siesgstarena.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "stats")
@JsonClass(generateAdapter = true)
data class TopStats(

    @field:Json(name = "name")
    val name: String,

    @PrimaryKey
    @field:Json(name = "language")
    val language: String,

    @field:Json(name = "acceptedCount")
    val acceptedCount: Int
)
