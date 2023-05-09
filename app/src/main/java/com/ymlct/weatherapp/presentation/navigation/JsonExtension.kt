package com.ymlct.weatherapp.presentation.navigation

import com.google.gson.Gson
import com.ymlct.weatherapp.domain.model.CityGeocodeModel

internal val gson = Gson()

internal fun CityGeocodeModel.toJson() = gson.toJson(this)

internal fun String.toGeocodeModel() = gson.fromJson(this, CityGeocodeModel::class.java)