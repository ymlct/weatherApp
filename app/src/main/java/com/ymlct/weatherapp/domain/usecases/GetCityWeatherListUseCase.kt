package com.ymlct.weatherapp.domain.usecases

import com.ymlct.weatherapp.domain.model.CityWeatherModel
import com.ymlct.weatherapp.domain.repository.Failure
import com.ymlct.weatherapp.domain.repository.RepoResponse
import com.ymlct.weatherapp.domain.repository.WeatherRepo
import com.ymlct.weatherapp.domain.utils.log
import javax.inject.Inject

class GetCityWeatherListUseCase
    @Inject constructor(private val weatherRepo: WeatherRepo)
    : BaseUseCase<BaseUseCase.None, List<CityWeatherModel>>()
{
        override suspend fun run(params: None):
                RepoResponse<List<CityWeatherModel>, Failure>
        {
            log("GetCityWeatherListUseCase::run(): enter in ${Thread.currentThread()}")
            val result = weatherRepo.getCityWeatherList()
            log("GetCityWeatherListUseCase::run(): exit in ${Thread.currentThread()}")
            return result
        }
}
