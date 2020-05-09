package com.kriticalflare.siesgstarena.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Contest(
    @field:Json(name="_id") val id: String,
    @field:Json(name="name") val name: String,
    @field:Json(name="code") val code: String?,
    @field:Json(name="description") val description: String,
    @field:Json(name="type") val contestType: ContestType,
    @field:Json(name="solutionVisibility")val solutionVisibility: SolutionVisibility?,
//    try with string for now
    @field:Json(name="startsAt") val startsAt: String,
//    try with string for now
    @field:Json(name="endsAt")val endsAt: String,
    @field:Json(name="contestAdmin") val contestAdmins: List<User>
)

enum class ContestType{
    @field:Json(name = "RATED") RATED,
    @field:Json(name="UNRATED") UNRATED
}
enum class SolutionVisibility{
    @field:Json(name="AFTER") AFTER,
    @field:Json(name="DURING") DURING,
    @field:Json(name="NEVER") NEVER
}