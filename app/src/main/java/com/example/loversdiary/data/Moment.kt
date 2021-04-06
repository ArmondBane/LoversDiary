package com.example.loversdiary.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "moments", foreignKeys = [ForeignKey(entity = Event::class, parentColumns = ["id"], childColumns = ["event_id"], onDelete = ForeignKey.CASCADE)])
@Parcelize
data class Moment (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val place: String = "",
    val date: Long = System.currentTimeMillis(),
    val note: String = "",
    val event_id: Int = 0
)