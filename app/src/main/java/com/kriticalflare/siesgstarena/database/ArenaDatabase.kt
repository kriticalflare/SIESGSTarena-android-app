package com.kriticalflare.siesgstarena.database

import androidx.room.*
import com.kriticalflare.siesgstarena.models.*

@Database(entities = [Contest::class,Problem::class,Blog::class], version = 1, exportSchema = false)
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