package com.ymlct.weatherapp.data.model.dto

import com.google.gson.annotations.SerializedName

data class CityWeatherDto(
    @SerializedName("fact")
    val currentWeather: CurrentCityWeatherDto = CurrentCityWeatherDto()
    )

data class CurrentCityWeatherDto(
    @SerializedName("temp")
    val temp: Int = Integer.MIN_VALUE,
    @SerializedName("feels_like")
    val feelsLike: Int = Integer.MIN_VALUE,
    @SerializedName("condition")
    val condition: String = ""
)