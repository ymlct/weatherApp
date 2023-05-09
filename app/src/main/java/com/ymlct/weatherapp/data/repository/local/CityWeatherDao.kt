package com.ymlct.weatherapp.data.repository.local

import androidx.room.*
import com.ymlct.weatherapp.data.model.entity.CityWeatherEntity

@Dao
interface CityWeatherDao {

    @Query("SELECT * FROM city_weather")
    fun getAll(): List<CityWeatherEntity>

    @Query("SELECT * FROM city_weather WHERE city_name LIKE :city")
    fun getByCityName(city: String): List<CityWeatherEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: CityWeatherEntity)

    @Update
    fun update(entity: CityWeatherEntity)

    @Delete
    fun delete(entity: CityWeatherEntity)
}