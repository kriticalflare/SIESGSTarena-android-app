package com.kriticalflare.siesgstarena.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "blogs")
@JsonClass(generateAdapter = true)
data class Blog(

    @field:Json(name = "owner")
    val owner: User,

    @PrimaryKey
    @field:Json(name = "_id")
    val id: String,

    @field:Json(name = "title")
    val title: String,

    @field:Json(name = "tags")
    val tags: List<String>,

    @field:Json(name = "upvotes")
    val upvotes: Int,

    @field:Json(name = "downvotes")
    val downvotes: Int,

    @field:Json(name = "createdAt")
    val createdAt: String,

    @field:Json(name = "updatedAt")
    val updatedAt: String
)