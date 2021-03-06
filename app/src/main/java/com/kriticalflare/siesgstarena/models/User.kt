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
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@Entity(tableName = "users")
@JsonClass(generateAdapter = true)
data class User(
    @PrimaryKey
    @field:Json(name = "_id") val id: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "username") val username: String,
    @field:Json(name = "email") val email: String?,
    @field:Json(name = "ratings") val ratings: Int?,
    @field:Json(name = "about") val about: String?,
    @field:Json(name = "codechefLink") val codechefLink: String?,
    @field:Json(name = "codeforcesLink") val codeforcesLink: String?,
    @field:Json(name = "githubLink") val githubLink: String?
)

class UserTypeConverter : KoinComponent {

    private val moshi: Moshi by inject()

    @TypeConverter
    fun userToString(user: User): String {
        return UserJsonAdapter(moshi).toJson(user)
    }

    @TypeConverter
    fun fromStringToUserList(data: String): User? {
        return UserJsonAdapter(moshi).fromJson(data)
    }
}

class UserListTypeConverter : KoinComponent {
    private val moshi: Moshi by inject()
    private val userListAdapter: JsonAdapter<List<User>> = moshi.adapter(
        Types.newParameterizedType(
        List::class.java,
        User::class.java
    ))

    @TypeConverter
    fun userListToString(users: List<User>): String? {
        return userListAdapter.toJson(users)
    }

    @TypeConverter
    fun fromStringToUserList(data: String?): List<User>? {
        if (data == null) {
            return Collections.emptyList()
        }
        return userListAdapter.fromJson(data)
    }
}
