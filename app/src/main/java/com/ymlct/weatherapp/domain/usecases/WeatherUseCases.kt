package com.ymlct.weatherapp.domain.usecases

import com.ymlct.weatherapp.domain.usecases.GetWeatherByCoordsUseCase
import com.ymlct.weatherapp.domain.usecases.GetWeatherSourceRefUseCase

data class WeatherUseCases(
    val getCityWeatherListUseCase: GetCityWeatherListUseCase,
    val getCoordsByCityNameUseCase: GetCoordsByCityNameUseCase,
    val getWeatherByCoordsUseCase: GetWeatherByCoordsUseCase,
    val getCityCoordsSourceRefUseCase: GetCityCoordsSourceRefUseCase,
    val getWeatherSourceRefUseCase: GetWeatherSourceRefUseCase,
)
