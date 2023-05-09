package com.ymlct.weatherapp.presentation.viewmodel

sealed class ScreenEvent {
    object Nothing: ScreenEvent()
    data class DataLoadingError(val msg: String) : ScreenEvent()
}