package com.kriticalflare.siesgstarena.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.squareup.moshi.*
import java.util.*

@Entity(tableName = "problems")
@JsonClass(generateAdapter = true)
data class Problem(

    @PrimaryKey
    @field:Json(name = "_id")
    val id: String,

    @field:Json(name = "code")
    val code: String,

    @field:Json(name = "contestCode")
    val contestCode: String,

    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "points")
    val points: Int,

    @field:Json(name = "tags")
    val tags: List<String>
)

class TagsTypeConverter{

    private val moshi = Moshi.Builder().build()
    private val tagsListAdapter: JsonAdapter<List<String>> = moshi.adapter(Types.newParameterizedType(
        List::class.java,
        String::class.java
    ))

    @TypeConverter
    fun fromTagToString(tags: List<String>): String?{
        return tagsListAdapter.toJson(tags)
    }

    @TypeConverter
    fun fromStringToTagsList(data: String?): List<String>?{
        if(data == null){
            return Collections.emptyList()
        }
        return  tagsListAdapter.fromJson(data)
    }
}