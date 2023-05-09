package com.ymlct.weatherapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ymlct.weatherapp.domain.model.CityGeocodeModel
import com.ymlct.weatherapp.domain.model.CityWeatherModel
import com.ymlct.weatherapp.domain.repository.Failure
import com.ymlct.weatherapp.domain.usecases.BaseUseCase
import com.ymlct.weatherapp.domain.usecases.CoordsParams
import com.ymlct.weatherapp.domain.usecases.WeatherUseCases
import com.ymlct.weatherapp.domain.utils.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherByCityViewModel
@Inject constructor(private val useCases: WeatherUseCases) : ViewModel() {

    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.Initial)
    val screenState = _screenState.asStateFlow()

    private val _screenEvent = MutableSharedFlow<ScreenEvent>()
    val screenEvent = _screenEvent.asSharedFlow()

    private val _weatherByCityList = MutableStateFlow<List<CityWeatherModel>>(emptyList())
    internal val weatherByCityList: StateFlow<List<CityWeatherModel>>
    get() {
//        log("WeatherByCityViewModel.weatherByCityList::get()")
        return _weatherByCityList.asStateFlow()
    }

    init {
        log("WeatherByCityViewModel::init()")
        viewModelScope.launch {
            useCases.getWeatherSourceRefUseCase().collect {
                _weatherByCityList.value = it
            }
        }
        viewModelScope.launch {
            useCases.getCityWeatherListUseCase(
                params = BaseUseCase.None(),
                scope = viewModelScope
            )
        }
    }

    internal fun requestWeatherByCoords(cityCoordinates: CityGeocodeModel) {
        useCases.getWeatherByCoordsUseCase(
            params = CoordsParams(cityGeocode = cityCoordinates),
            scope = viewModelScope
        ) { response ->
            response.execute(
                onSuccess = ::processResult,
                onFailure = ::processFailure
            )
        }
    }

    private fun processResult(response: CityWeatherModel) {
//        viewModelScope.launch {
//            if (response.value.isValid()) response.collect { _weatherData.value = it }
//            else _screenEvent.emit(ScreenEvent.DataLoadingError(" Error"))
//        }
    }

    private fun processFailure(response: Failure) {

    }

    fun showError() {

    }

}