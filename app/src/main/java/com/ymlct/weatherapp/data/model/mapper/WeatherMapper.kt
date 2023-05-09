package com.ymlct.weatherapp.data.model.mapper

import com.ymlct.weatherapp.data.model.dto.CurrentCityWeatherDto
import com.ymlct.weatherapp.data.model.dto.CityWeatherDto
import com.ymlct.weatherapp.data.model.entity.CityWeatherEntity
import com.ymlct.weatherapp.data.repository.toCurrentWeatherModel
import com.ymlct.weatherapp.data.repository.toJson
import com.ymlct.weatherapp.data.repository.toMapOfCityLocalNames
import com.ymlct.weatherapp.domain.model.CityGeocodeModel
import com.ymlct.weatherapp.domain.model.CurrentWeatherModel
import com.ymlct.weatherapp.domain.model.CityWeatherModel

internal fun CityWeatherDto.toModel(
    cityGeocode: CityGeocodeModel,
    timestamp: Long,
) = CityWeatherModel(
    cityGeocode = cityGeocode,
    currentWeather = currentWeather.toModel(),
    timestamp = timestamp
)

internal fun CurrentCityWeatherDto.toModel() = CurrentWeatherModel(
    temp = temp,
    feelsLike = feelsLike,
    condition = condition
)


internal fun CityWeatherModel.toEntity() =
    CityWeatherEntity(
        cityName = cityGeocode.cityName,
        latitude = cityGeocode.latitude,
        longitude = cityGeocode.longitude,
        countryCode = cityGeocode.countryCode,
        cityLocalNamesJson = cityGeocode.localNames.toJson(),
        currentWeatherJson = currentWeather.toJson(),
        timestamp = timestamp
    )

internal fun CityWeatherEntity.toCityWeatherModel() =
    CityWeatherModel(
        cityGeocode = toCityGeocodeModel(),
        currentWeather = currentWeatherJson.toCurrentWeatherModel(),
        timestamp = timestamp
    )

internal fun CityWeatherEntity.toCityGeocodeModel() =
    CityGeocodeModel(
        cityName = cityName,
        localNames = cityLocalNamesJson.toMapOfCityLocalNames(),
        latitude = latitude,
        longitude = longitude,
        countryCode = countryCode
    )

