package com.ymlct.weatherapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ymlct.weatherapp.domain.model.CityGeocodeModel
import com.ymlct.weatherapp.domain.repository.Failure
import com.ymlct.weatherapp.domain.usecases.CityNameParams
import com.ymlct.weatherapp.domain.usecases.WeatherUseCases
import com.ymlct.weatherapp.domain.utils.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FindCityByCoordsViewModel
@Inject constructor(private val useCases: WeatherUseCases) : ViewModel()
{

    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.Initial)
    val screenState = _screenState.asStateFlow()

    private val _screenEvent = MutableSharedFlow<ScreenEvent>()
    val screenEvent = _screenEvent.asSharedFlow()

    private val _coordsForCityNameList = MutableStateFlow<List<CityGeocodeModel>>(emptyList())
    internal val coordsForCityNameList: StateFlow<List<CityGeocodeModel>>
    get() {
        log("FindCityByCoordsViewModel.coordsForCityNameList::get()")
        return _coordsForCityNameList.asStateFlow()
    }

    init {
        log("FindCityByCoordsViewModel::init()")
        viewModelScope.launch {
            useCases.getCityCoordsSourceRefUseCase().collect {
                _coordsForCityNameList.value = it
            }
        }
    }

    internal fun requestCoordsForCityName(cityName: String) {
        useCases.getCoordsByCityNameUseCase(
            params = CityNameParams(cityName = cityName),
            scope = viewModelScope
        ) { response ->
            response.execute(
                onSuccess = ::processResult,
                onFailure = ::processFailure
            )
        }
    }

    private fun processResult(response: List<CityGeocodeModel>) { }

    private fun processFailure(response: Failure) { }

    fun showError() { }

}