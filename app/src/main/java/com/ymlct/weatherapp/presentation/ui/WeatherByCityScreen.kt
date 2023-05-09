package com.ymlct.weatherapp.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ymlct.weatherapp.domain.model.CityGeocodeModel
import com.ymlct.weatherapp.presentation.ui.components.CityWeatherCard
import com.ymlct.weatherapp.presentation.viewmodel.WeatherByCityViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WeatherByCityScreen(
    onNavigateToFindCityCoordsScreen: () -> Unit,
    onCollectCityCoordinates: () -> CityGeocodeModel?
) {
    val viewModel: WeatherByCityViewModel = hiltViewModel()

    LaunchedEffect(key1 = Unit) {
        onCollectCityCoordinates().let {
            if (it != null) viewModel.requestWeatherByCoords(cityCoordinates = it)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar {
//                IconButton(onClick = { }) {
//                    Icon(imageVector = Icons.Filled.Menu, contentDescription = null)
//                }
//                Text("METANIT.COM", fontSize = 22.sp)
                Spacer(Modifier.weight(1f, true))

//                IconButton(onClick = { }) {
//                    Icon(Icons.Filled.Info, contentDescription = "Информация о приложении")
//                }
                IconButton(onClick = onNavigateToFindCityCoordsScreen) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Поиск")
                }
            }
        }
    ) {
        val list by viewModel.weatherByCityList.collectAsStateWithLifecycle()

        LazyColumn(
            modifier = Modifier.padding(top = 8.dp).fillMaxSize()
        ) {
            items (count = list.size) {
                CityWeatherCard(
                    modifier = Modifier.padding(top = 12.dp),
                    list[it],
                    onClick = { }
                )
            }
        }
    }
}

//@Preview
//@Composable
//private fun Preview() {
//    WeatherByCityScreen(
//        onNavigateToFindCityCoordsScreen = { }
//    )
//}