package com.ymlct.weatherapp.domain.usecases

import com.ymlct.weatherapp.domain.repository.WeatherRepo
import javax.inject.Inject

class GetWeatherSourceRefUseCase
    @Inject constructor(private val weatherRepo: WeatherRepo) {
    operator fun invoke() = weatherRepo.weatherByCity
}
