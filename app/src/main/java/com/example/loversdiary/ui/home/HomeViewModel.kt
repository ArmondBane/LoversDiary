package com.example.loversdiary.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.loversdiary.data.UserDao
import com.example.loversdiary.ui.EDIT_SETTINGS_RESULT_OK
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
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
    userDao: UserDao
)  : ViewModel() {

    val user = userDao.getUser().asLiveData()

    private val homeEventChannel = Channel<HomeEvent>()
    val homeEvent = homeEventChannel.receiveAsFlow()

    fun onSettingsResult(result: Int) {
        when (result) {
            EDIT_SETTINGS_RESULT_OK -> showSettingsSavedConfirmationMessage("Настройки успешно сохранены")
        }
    }

    private fun showSettingsSavedConfirmationMessage(text: String) = viewModelScope.launch {
        homeEventChannel.send(HomeEvent.ShowSettingsSavedConfirmationMessage(text))
    }

    sealed class HomeEvent {
        data class ShowSettingsSavedConfirmationMessage(val msg: String) : HomeEvent()
    }
}