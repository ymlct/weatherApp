package com.ymlct.weatherapp.data.repository.local

import com.ymlct.weatherapp.data.model.entity.CityWeatherEntity

internal class LocalDataSource(
    private val cityWeatherDao: CityWeatherDao
) {

    internal suspend fun getCityWeatherList(): List<CityWeatherEntity> {

        return cityWeatherDao.getAll()
    }

    internal suspend fun updateCityWeather(cityWeather: CityWeatherEntity) {

        cityWeatherDao.insert(cityWeather)
    }
}