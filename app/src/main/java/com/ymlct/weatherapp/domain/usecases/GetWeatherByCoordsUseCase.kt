package com.ymlct.weatherapp.domain.usecases

import com.ymlct.weatherapp.domain.model.CityGeocodeModel
import com.ymlct.weatherapp.domain.model.CityWeatherModel
import com.ymlct.weatherapp.domain.repository.Failure
import com.ymlct.weatherapp.domain.repository.RepoResponse
import com.ymlct.weatherapp.domain.repository.WeatherRepo
import com.ymlct.weatherapp.domain.utils.log
import javax.inject.Inject

class GetWeatherByCoordsUseCase
    @Inject constructor(private val weatherRepo: WeatherRepo)
    : BaseUseCase<CoordsParams, CityWeatherModel>()
{
        override suspend fun run(params: CoordsParams):
                RepoResponse<CityWeatherModel, Failure>
        {
            log("GetWeatherByCoordsUseCase: enter method run() in ${Thread.currentThread()}")
            val result = weatherRepo.getCityWeatherByCoords(params)
            log("GetWeatherByCoordsUseCase: exit method run() in ${Thread.currentThread()}")
            return result
        }
}

data class CoordsParams(
    val cityGeocode: CityGeocodeModel
)