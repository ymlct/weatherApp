package com.ymlct.weatherapp.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "city_weather",
    primaryKeys = ["latitude", "longitude"]
)
data class CityWeatherEntity(

    @ColumnInfo(name = "city_name")
    val cityName: String,

    @ColumnInfo(name = "latitude")
    val latitude: String = "",

    @ColumnInfo(name = "longitude")
    val longitude: String = "",

    @ColumnInfo(name = "country_code")
    val countryCode: String,

    @ColumnInfo(name = "city_local_names_json")
    val cityLocalNamesJson: String = "",

    @ColumnInfo(name = "current_weather_json")
    val currentWeatherJson: String = "",

    @ColumnInfo(name = "timestamp")
    val timestamp: Long = 0L,

)