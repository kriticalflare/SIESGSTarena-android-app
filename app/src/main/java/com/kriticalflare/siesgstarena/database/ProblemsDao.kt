package com.kriticalflare.siesgstarena.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kriticalflare.siesgstarena.models.Problem

@Dao
interface ProblemsDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProblems(contest: List<Problem>)

    @Query("SELECT * FROM problems")
    fun getAllProblem(): LiveData<List<Problem>>

    @Query("DELETE FROM problems")
    fun deleteAllProblems()
}