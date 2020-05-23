package com.kriticalflare.siesgstarena.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.squareup.moshi.*
import kotlinx.coroutines.joinAll
import org.koin.core.KoinComponent
import org.koin.core.context.GlobalContext.get
import org.koin.core.inject
import java.util.*

@Entity(tableName = "users")
@JsonClass(generateAdapter = true)
data class User(
    @PrimaryKey
    @field:Json(name="_id") val id: String,
    @field:Json(name="name") val name: String,
    @field:Json(name="username") val username: String,
    @field:Json(name="email") val email: String?,
    @field:Json(name="ratings") val ratings: Int?,
    @field:Json(name="about") val about: String?,
    @field:Json(name="codechefLink") val codechefLink: String?,
    @field:Json(name="codeforcesLink") val codeforcesLink: String?,
    @field:Json(name="githubLink") val githubLink: String?
)

class UserTypeConverter : KoinComponent{

    private val moshi:Moshi by inject()

    @TypeConverter
    fun userToString(user: User): String{
        return UserJsonAdapter(moshi).toJson(user)
    }

    @TypeConverter
    fun fromStringToUserList(data: String): User? {
        return UserJsonAdapter(moshi).fromJson(data)
    }
}

class UserListTypeConverter : KoinComponent{
    private val moshi:Moshi by inject()
    private val userListAdapter: JsonAdapter<List<User>> = moshi.adapter(Types.newParameterizedType(
        List::class.java,
        User::class.java
    ))

    @TypeConverter
    fun userListToString(users: List<User>): String?{
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
