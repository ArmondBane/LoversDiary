package com.example.loversdiary.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.loversdiary.data.UserDao
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.time.ExperimentalTime
import kotlin.time.days

class HomeViewModel @ViewModelInject constructor(
    private val userDao: UserDao,
    @Assisted private val state: SavedStateHandle
)  : ViewModel() {

    val user = userDao.getUser()

    @RequiresApi(Build.VERSION_CODES.O)
    private val period = Period.between(
            Instant.ofEpochMilli(user.date_of_start).atZone(ZoneId.systemDefault()).toLocalDate(),
            LocalDate.now()
    )

    @RequiresApi(Build.VERSION_CODES.O)
    val daysLeft: Int = period.days

    @RequiresApi(Build.VERSION_CODES.O)
    val monthsLeft: Int = period.months

    @RequiresApi(Build.VERSION_CODES.O)
    val yearsLeft: Int = period.years
}