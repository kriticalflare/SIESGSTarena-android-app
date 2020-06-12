package com.kriticalflare.siesgstarena.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kriticalflare.siesgstarena.models.TopStats

@Dao
interface StatsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStats(contest: List<TopStats>)

    @Query("SELECT * FROM stats")
    fun getAllStats(): LiveData<List<TopStats>>

    @Query("DELETE FROM stats")
    fun deleteAllStats()
}
