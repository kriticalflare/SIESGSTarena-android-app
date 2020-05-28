package com.kriticalflare.siesgstarena.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kriticalflare.siesgstarena.models.Blog

@Dao
interface BlogsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBlogs(contest: List<Blog>)

    @Query("SELECT * FROM blogs")
    fun getAllBlogs(): LiveData<List<Blog>>

    @Query("DELETE FROM blogs")
    fun deleteAllBlogs()
}
