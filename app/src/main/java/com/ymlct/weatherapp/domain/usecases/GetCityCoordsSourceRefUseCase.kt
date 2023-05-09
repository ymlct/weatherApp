package com.ymlct.weatherapp.domain.usecases

import com.ymlct.weatherapp.domain.repository.WeatherRepo
import javax.inject.Inject

class GetCityCoordsSourceRefUseCase
    @Inject constructor(private val weatherRepo: WeatherRepo) {
    operator fun invoke() = weatherRepo.cityCoordinates
}
