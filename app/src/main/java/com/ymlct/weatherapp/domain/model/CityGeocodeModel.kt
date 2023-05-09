package com.ymlct.weatherapp.domain.model

data class CityGeocodeModel(
    val cityName: String = "",
    val localNames: Map<String, String> = emptyMap(),
    val latitude: String = "",
    val longitude: String = "",
    val countryCode: String = "",
    )

fun CityGeocodeModel.isValid() =
    cityName != "" &&
    latitude != "" &&
    longitude != ""
