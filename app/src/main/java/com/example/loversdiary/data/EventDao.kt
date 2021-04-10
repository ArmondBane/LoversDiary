package com.example.loversdiary.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {

    @Query("SELECT e.id, e.name FROM moments m, events e WHERE m.event_id=e.id")
    fun getEventsByMoments():  Flow<List<Event>>

    @Query("SELECT * FROM events")
    fun getAllEvents(): List<Event>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: Event)

    @Update
    suspend fun update(event: Event)

    @Delete
    suspend fun delete(event: Event)
}