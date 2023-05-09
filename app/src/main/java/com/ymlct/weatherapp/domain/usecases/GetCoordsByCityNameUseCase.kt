package com.ymlct.weatherapp.domain.usecases

import com.ymlct.weatherapp.domain.model.CityGeocodeModel
import com.ymlct.weatherapp.domain.repository.Failure
import com.ymlct.weatherapp.domain.repository.RepoResponse
import com.ymlct.weatherapp.domain.repository.WeatherRepo
import com.ymlct.weatherapp.domain.utils.log
import javax.inject.Inject

class GetCoordsByCityNameUseCase
    @Inject constructor(private val weatherRepo: WeatherRepo)
    : BaseUseCase<CityNameParams, List<CityGeocodeModel>>()
{
        override suspend fun run(params: CityNameParams):
                RepoResponse<List<CityGeocodeModel>, Failure>
        {
            log("GetCoordsByCityNameUseCase::run(): enter in ${Thread.currentThread()}")
            val result = weatherRepo.getCoordsByCityName(params)
            log("GetCoordsByCityNameUseCase::run(): exit in ${Thread.currentThread()}")
            return result
        }
}

data class CityNameParams(val cityName: String)