package com.kriticalflare.siesgstarena.models

import androidx.room.*
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "contests")
@JsonClass(generateAdapter = true)
data class Contest(

    @PrimaryKey
    @field:Json(name = "_id")
    var id: String,

    @field:Json(name = "name")
    var name: String,

    @field:Json(name = "code")
    var code: String?,

    @field:Json(name = "description")
    var description: String,

    @field:Json(name = "type")
    var contestType: ContestType,

    @field:Json(name = "startsAt")
    var startsAt: String,

    @field:Json(name = "endsAt")
    var endsAt: String,

    @field:Json(name = "contestAdmin")
    var contestAdmins: List<User>
)



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