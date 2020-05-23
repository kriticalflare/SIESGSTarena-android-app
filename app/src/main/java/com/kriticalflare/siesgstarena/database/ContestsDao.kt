package com.kriticalflare.siesgstarena.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kriticalflare.siesgstarena.models.Contest

@Dao
interface ContestsDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContests(contest: List<Contest>)

    @Query("SELECT * FROM contests")
    fun getAllContests(): LiveData<List<Contest>>

    @Query("DELETE FROM contests")
    fun deleteAllContests()

    @Query("SELECT * FROM contests")
    fun getContestsWidget(): List<Contest>
}