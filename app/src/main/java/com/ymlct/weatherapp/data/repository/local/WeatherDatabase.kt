package com.ymlct.weatherapp.data.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ymlct.weatherapp.data.model.entity.CityWeatherEntity


@Database(
    entities = [
        CityWeatherEntity::class,
    ],
    version = 1
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun cityWeatherDao(): CityWeatherDao
}