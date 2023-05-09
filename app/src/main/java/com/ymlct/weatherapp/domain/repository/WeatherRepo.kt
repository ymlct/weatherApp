package com.ymlct.weatherapp.domain.repository

import com.ymlct.weatherapp.domain.model.CityGeocodeModel
import com.ymlct.weatherapp.domain.model.CityWeatherModel
import com.ymlct.weatherapp.domain.usecases.CoordsParams
import com.ymlct.weatherapp.domain.usecases.CityNameParams
import kotlinx.coroutines.flow.StateFlow

interface WeatherRepo {

    val cityCoordinates: StateFlow<List<CityGeocodeModel>>

    val weatherByCity: StateFlow<List<CityWeatherModel>>

    suspend fun getCoordsByCityName(
        params: CityNameParams
    ): RepoResponse<List<CityGeocodeModel>, Failure>

    suspend fun getCityWeatherByCoords(
        params: CoordsParams
    ): RepoResponse<CityWeatherModel, Failure>

    suspend fun getCityWeatherList(): RepoResponse<List<CityWeatherModel>, Failure>

}

