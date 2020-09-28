package com.kriticalflare.siesgstarena.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.util.Collections
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

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
) {
    fun createProblemUrl(): String = "http://arena.siesgst.ac.in/contest/$contestCode/problem/$code"
}

@OptIn(KoinApiExtension::class)
class TagsTypeConverter : KoinComponent {

    private val moshi: Moshi by inject()
    private val tagsListAdapter: JsonAdapter<List<String>> = moshi.adapter(
        Types.newParameterizedType(
        List::class.java,
        String::class.java
    ))

    @TypeConverter
    fun fromTagToString(tags: List<String>): String? {
        return tagsListAdapter.toJson(tags)
    }

    @TypeConverter
    fun fromStringToTagsList(data: String?): List<String>? {
        if (data == null) {
            return Collections.emptyList()
        }
        return tagsListAdapter.fromJson(data)
    }
}
