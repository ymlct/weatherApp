package com.ymlct.weatherapp.data.repository.remote

import com.ymlct.weatherapp.data.model.dto.GeocodeDto
import com.ymlct.weatherapp.data.model.dto.CityWeatherDto
import com.ymlct.weatherapp.data.repository.remote.api.OpenWeatherApi
import com.ymlct.weatherapp.data.repository.remote.api.YandexWeatherApi
import com.ymlct.weatherapp.data.repository.remote.response.ApiResponse
import javax.inject.Inject

internal class RemoteDataSource(
    private val yandexWeatherApi: YandexWeatherApi,
    private val openWeatherApi: OpenWeatherApi
) {

    internal suspend fun requestCoordsByCityName(
        cityName: String,
    ): ApiResponse<List<GeocodeDto>, String> {
        return openWeatherApi.getCoordsByCityName(
            cityName = cityName
        )
    }

    internal suspend fun requestWeatherByCoords(
        latitude: String,
        longitude: String,
    ): ApiResponse<CityWeatherDto, String> {
        return yandexWeatherApi.getForecastByCoords(
            latitude = latitude,
            longitude = longitude
        )
    }
}