package com.kriticalflare.siesgstarena.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kriticalflare.siesgstarena.models.Blog
import com.kriticalflare.siesgstarena.models.Contest
import com.kriticalflare.siesgstarena.models.ContestTypeConverter
import com.kriticalflare.siesgstarena.models.Problem
import com.kriticalflare.siesgstarena.models.TagsTypeConverter
import com.kriticalflare.siesgstarena.models.UserListTypeConverter
import com.kriticalflare.siesgstarena.models.UserTypeConverter

@Database(entities = [Contest::class, Problem::class, Blog::class], version = 1, exportSchema = false)
@TypeConverters(
    ContestTypeConverter::class,
    UserTypeConverter::class,
    UserListTypeConverter::class,
    TagsTypeConverter::class
)
abstract class ArenaDatabase : RoomDatabase() {

    abstract fun contestDao(): ContestsDao

    abstract fun problemsDao(): ProblemsDao

    abstract fun blogsDao(): BlogsDao
}
