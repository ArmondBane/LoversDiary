package com.example.loversdiary.data

import android.graphics.Bitmap
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "user")
@Parcelize
data class User (
    val user_name: String = "",
    val partner_name: String = "",
    val date_of_start: Long = System.currentTimeMillis()
)