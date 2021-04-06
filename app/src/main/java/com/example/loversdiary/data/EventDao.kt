package com.example.loversdiary.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {

    @Query("SELECT * FROM events WHERE id=:momentId")
    fun getEventsByMomentId(momentId: Int): List<Event>

    @Query("SELECT * FROM events")
    fun getAllEvents(): Flow<List<Event>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(film: Event)

    @Update
    suspend fun update(film: Event)

    @Delete
    suspend fun delete(film: Event)
}