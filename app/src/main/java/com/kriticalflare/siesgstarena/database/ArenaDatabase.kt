package com.kriticalflare.siesgstarena.database

import androidx.room.*
import com.kriticalflare.siesgstarena.models.*

@Database(entities = [Contest::class,Problem::class], version = 1, exportSchema = false)
@TypeConverters(
    ContestTypeConverter::class,
    UserListTypeConverter::class,
    TagsTypeConverter::class
)
abstract class ArenaDatabase : RoomDatabase() {

    abstract fun contestDao(): ContestsDao

    abstract fun problemsDao(): ProblemsDao
}