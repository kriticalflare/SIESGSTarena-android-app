package com.kriticalflare.siesgstarena.database

import androidx.room.*
import com.kriticalflare.siesgstarena.models.Contest
import com.kriticalflare.siesgstarena.models.ContestTypeConverter
import com.kriticalflare.siesgstarena.models.UserListTypeConverter

@Database(entities = [Contest::class], version = 1, exportSchema = false)
@TypeConverters(
    ContestTypeConverter::class,
    UserListTypeConverter::class
)
abstract class ArenaDatabase : RoomDatabase() {

    abstract fun contestDao(): ContestsDao
}