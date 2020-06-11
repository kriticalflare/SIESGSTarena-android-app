package com.kriticalflare.siesgstarena.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "contests")
@JsonClass(generateAdapter = true)
data class Contest(

    @PrimaryKey
    @field:Json(name = "_id")
    val id: String,

    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "code")
    val code: String,

    @field:Json(name = "description")
    val description: String,

    @field:Json(name = "type")
    val contestType: ContestType,

    @field:Json(name = "startsAt")
    val startsAt: String,

    @field:Json(name = "endsAt")
    val endsAt: String,

    @field:Json(name = "contestAdmin")
    val contestAdmins: List<User>
) {
    fun createContestUrl(): String = "http://arena.siesgst.ac.in/contest/$code"
}

enum class ContestType {
    @field:Json(name = "RATED")
    RATED,

    @field:Json(name = "UNRATED")
    UNRATED,

    @field:Json(name = "UNKNOWN")
    UNKNOWN
}

class ContestTypeConverter {
    @TypeConverter
    fun fromContestType(contestType: ContestType): String? {
        return contestType.toString()
    }

    @TypeConverter
    fun toContestType(type: String?): ContestType {
        return when (type) {
            "RATED" -> ContestType.RATED
            "UNRATED" -> ContestType.UNRATED
            else -> ContestType.UNKNOWN
        }
    }
}

/* Deprecated

@field:Json(name = "solutionVisibility")
var solutionVisibility: SolutionVisibility?,

class SolutionVisibilityTypeConverter {
    @TypeConverter
    fun fromSolutionVisibility(visibility: SolutionVisibility): String? {
        return visibility.toString()
    }

    @TypeConverter
    fun toSolutionVisibility(type: String): SolutionVisibility? {
        return when (type) {
            "AFTER" -> SolutionVisibility.AFTER
            "DURING" -> SolutionVisibility.DURING
            "NEVER" -> SolutionVisibility.NEVER
            else -> SolutionVisibility.UNKNOWN
        }
    }
}

enum class SolutionVisibility {
    @field:Json(name = "AFTER")
    AFTER,

    @field:Json(name = "DURING")
    DURING,

    @field:Json(name = "NEVER")
    NEVER,

    @field:Json(name = "UNKNOWN")
    UNKNOWN
}

*/
