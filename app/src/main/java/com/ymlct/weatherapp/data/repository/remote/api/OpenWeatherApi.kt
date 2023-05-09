package com.ymlct.weatherapp.data.repository.remote.api

import com.ymlct.weatherapp.BuildConfig
import com.ymlct.weatherapp.data.model.dto.GeocodeDto
import com.ymlct.weatherapp.data.repository.remote.response.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET("geo/1.0/direct?")
    suspend fun getCoordsByCityName(
        @Query("q")cityName: String,
        @Query("limit")limit: Int = 5,
        @Query("appid")apiKey: String = BuildConfig.OPEN_WEATHER_API_KEY,
    ) : ApiResponse<List<GeocodeDto>, String>
}