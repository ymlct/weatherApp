package com.ymlct.weatherapp.presentation.viewmodel

sealed class ScreenState {
    object Initial : ScreenState()
    object Loading : ScreenState()
}