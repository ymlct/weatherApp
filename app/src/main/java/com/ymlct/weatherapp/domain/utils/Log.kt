package com.ymlct.weatherapp.domain.utils

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

private const val WEATHER_APP_TAG = "weatherApp"

private val _logStack = MutableStateFlow("Log:\n")
val logStack = _logStack.asStateFlow()

fun log(msg: String) {
    _logStack.value = "${_logStack.value}\n$msg\n"
    Log.e(WEATHER_APP_TAG, msg)
}

fun log(e: Exception) {
    Log.e(WEATHER_APP_TAG, e.toString())
}

