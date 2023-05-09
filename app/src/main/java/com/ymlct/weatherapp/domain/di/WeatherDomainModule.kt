package com.ymlct.weatherapp.domain.di

import com.ymlct.weatherapp.domain.repository.WeatherRepo
import com.ymlct.weatherapp.domain.usecases.GetCityCoordsSourceRefUseCase
import com.ymlct.weatherapp.domain.usecases.GetCityWeatherListUseCase
import com.ymlct.weatherapp.domain.usecases.GetCoordsByCityNameUseCase
import com.ymlct.weatherapp.domain.usecases.GetWeatherSourceRefUseCase
import com.ymlct.weatherapp.domain.usecases.GetWeatherByCoordsUseCase
import com.ymlct.weatherapp.domain.usecases.WeatherUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object WeatherDomainModule {

    @Provides
    @ViewModelScoped
    fun provideUseCases(weatherRepo: WeatherRepo): WeatherUseCases {
        return WeatherUseCases(
            getCityWeatherListUseCase = GetCityWeatherListUseCase(weatherRepo),
            getCoordsByCityNameUseCase = GetCoordsByCityNameUseCase(weatherRepo),
            getWeatherByCoordsUseCase = GetWeatherByCoordsUseCase(weatherRepo),
            getCityCoordsSourceRefUseCase = GetCityCoordsSourceRefUseCase(weatherRepo),
            getWeatherSourceRefUseCase = GetWeatherSourceRefUseCase(weatherRepo),
        )
    }
}