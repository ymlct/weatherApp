package com.ymlct.weatherapp.domain.model

data class CityWeatherModel(
    val cityGeocode: CityGeocodeModel = CityGeocodeModel(),
    val currentWeather: CurrentWeatherModel = CurrentWeatherModel(),
    val timestamp:Long = 0L
    )

data class CurrentWeatherModel(
    val temp: Int = Integer.MIN_VALUE,
    val feelsLike: Int = Integer.MIN_VALUE,
    val condition: String = ""
)

fun CityWeatherModel.isValid(): Boolean {
    return currentWeather.temp != Integer.MIN_VALUE &&
            currentWeather.feelsLike != Integer.MIN_VALUE &&
            currentWeather.condition != ""
}