package com.example.loversdiary.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MomentDao {

    @Query("SELECT * FROM moments")
    fun getAllMoments(): Flow<List<Moment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(film: Moment)

    @Update
    suspend fun update(film: Moment)

    @Delete
    suspend fun delete(film: Moment)
}