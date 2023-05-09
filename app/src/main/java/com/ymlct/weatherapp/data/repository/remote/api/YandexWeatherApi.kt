package com.ymlct.weatherapp.data.repository.remote.api

import com.ymlct.weatherapp.BuildConfig
import com.ymlct.weatherapp.data.model.dto.CityWeatherDto
import com.ymlct.weatherapp.data.repository.remote.response.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface YandexWeatherApi {

    @Headers("X-Yandex-API-Key: ${BuildConfig.YANDEX_WEATHER_API_KEY}")
    @GET("v2/forecast?")
    suspend fun getForecastByCoords(
        @Query("lat")latitude: String,
        @Query("lon")longitude: String,
        @Query("lang")language: String = "ru_RU",
        @Query("limit")limit: Int = 1,
        @Query("hour")hour: Boolean = false,
        @Query("extra")extra: Boolean = false,
    ) : ApiResponse<CityWeatherDto, String>
}