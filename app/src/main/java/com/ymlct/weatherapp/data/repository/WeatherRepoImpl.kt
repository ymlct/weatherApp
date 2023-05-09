package com.ymlct.weatherapp.data.repository

import com.ymlct.weatherapp.data.model.mapper.toCityWeatherModel
import com.ymlct.weatherapp.data.model.mapper.toEntity
import com.ymlct.weatherapp.data.model.mapper.toModel
import com.ymlct.weatherapp.data.repository.local.CityWeatherDao
import com.ymlct.weatherapp.data.repository.local.LocalDataSource
import com.ymlct.weatherapp.data.repository.remote.response.ApiResponse
import com.ymlct.weatherapp.data.repository.remote.api.OpenWeatherApi
import com.ymlct.weatherapp.data.repository.remote.RemoteDataSource
import com.ymlct.weatherapp.data.repository.remote.api.YandexWeatherApi
import com.ymlct.weatherapp.domain.model.CityGeocodeModel
import com.ymlct.weatherapp.domain.model.CityWeatherModel
import com.ymlct.weatherapp.domain.repository.Failure
import com.ymlct.weatherapp.domain.repository.WeatherRepo
import com.ymlct.weatherapp.domain.repository.RepoResponse
import com.ymlct.weatherapp.domain.usecases.CityNameParams
import com.ymlct.weatherapp.domain.usecases.CoordsParams
import com.ymlct.weatherapp.domain.utils.log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class WeatherRepoImpl(
    yandexWeatherApi: YandexWeatherApi,
    openWeatherApi: OpenWeatherApi,
    cityWeatherDao: CityWeatherDao
): WeatherRepo {

    private val remoteDataSource = RemoteDataSource(yandexWeatherApi, openWeatherApi)
    private val localDataSource = LocalDataSource(cityWeatherDao)

    private val _weatherByCity = MutableStateFlow<List<CityWeatherModel>>(emptyList())
    override val weatherByCity = _weatherByCity.asStateFlow()

    private val _cityCoordinates = MutableStateFlow<List<CityGeocodeModel>>(emptyList())
    override val cityCoordinates = _cityCoordinates.asStateFlow()


    override suspend fun getCoordsByCityName(
        params: CityNameParams
    ): RepoResponse<List<CityGeocodeModel>, Failure>
    {
        return with (
            remoteDataSource.requestCoordsByCityName(params.cityName)
        ) {
            when (this) {
                is ApiResponse.Success -> {
                    _cityCoordinates.value = data.map { it.toModel() }
                    RepoResponse.Succeeded(_cityCoordinates.value)
                }
                is ApiResponse.ApiError -> {
                    RepoResponse.Failed(Failure.ServerError)
                }
                is ApiResponse.NetworkError -> {
                    RepoResponse.Failed(Failure.NetworkConnection)
                }
                is ApiResponse.UnknownError -> {
                    RepoResponse.Failed(Failure.ServerError)
                }
            }
        }
    }

    override suspend fun getCityWeatherByCoords(
        params: CoordsParams
    ): RepoResponse<CityWeatherModel, Failure>
    {
        return with (
            remoteDataSource.requestWeatherByCoords(
                params.cityGeocode.latitude,
                params.cityGeocode.longitude
            )
        ) {
            when (this) {
                is ApiResponse.Success -> {
                    val weatherModel = data.toModel(
                        cityGeocode = params.cityGeocode,
                        timestamp = System.currentTimeMillis()
                    )

                    _weatherByCity.value = buildList {
                        if (_weatherByCity.value.find {
                            it.cityGeocode.latitude == weatherModel.cityGeocode.latitude &&
                            it.cityGeocode.longitude == weatherModel.cityGeocode.longitude
                            } == null) add(weatherModel)
                        addAll(_weatherByCity.value)
                    }

                    localDataSource.updateCityWeather(weatherModel.toEntity())

                    RepoResponse.Succeeded(weatherModel)

                }
                is ApiResponse.ApiError -> {
                    RepoResponse.Failed(Failure.ServerError)
                }
                is ApiResponse.NetworkError -> {
                    RepoResponse.Failed(Failure.NetworkConnection)
                }
                is ApiResponse.UnknownError -> {
                    RepoResponse.Failed(Failure.ServerError)
                }
            }
        }
    }

    override suspend fun getCityWeatherList(): RepoResponse<List<CityWeatherModel>, Failure> {
        var result: RepoResponse<List<CityWeatherModel>, Failure> =
            RepoResponse.Failed(Failure.DataBaseError)

        try {
            val list = localDataSource.getCityWeatherList().map { it.toCityWeatherModel() }
            result = RepoResponse.Succeeded(list)
            _weatherByCity.update { list }

            log("WeatherRepoImpl::getCityWeatherList(): result=$result")

        } catch (e: Exception) {
            log("WeatherRepoImpl::getCityWeatherList(): exception=$e")
        }

        return result
    }
}