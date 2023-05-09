package com.ymlct.weatherapp.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ymlct.weatherapp.domain.model.CurrentWeatherModel
import java.lang.reflect.Type


internal val gson = Gson()

internal fun CurrentWeatherModel.toJson() = gson.toJson(this)
internal fun String.toCurrentWeatherModel() = gson.fromJson(this, CurrentWeatherModel::class.java)

internal fun Map<String, String>.toJson() = gson.toJson(this)
internal val itemsMapType: Type = object : TypeToken<Map<String, String>>() {}.type
internal fun String.toMapOfCityLocalNames() = gson.fromJson<Map<String, String>>(this, itemsMapType)

