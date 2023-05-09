package com.ymlct.weatherapp.data.model.mapper

import com.ymlct.weatherapp.data.model.dto.GeocodeDto
import com.ymlct.weatherapp.domain.model.CityGeocodeModel

internal fun GeocodeDto.toModel() = CityGeocodeModel(
    cityName = name,
    localNames = localNames,
    latitude = latitude,
    longitude = longitude,
    countryCode = country
)