package com.ymlct.weatherapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ymlct.weatherapp.presentation.ui.FindCityCoordsScreen
import com.ymlct.weatherapp.presentation.ui.WeatherByCityScreen

private const val WEATHER_BY_CITY_SCREEN = "weatherByCityScreen"
private const val FIND_CITY_COORDS_SCREEN = "findCityCoordsScreen"

private const val CITY_COORDINATES_KEY = "cityCoordinates"

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = WEATHER_BY_CITY_SCREEN) {

        composable(route = WEATHER_BY_CITY_SCREEN) {
            WeatherByCityScreen(
                onNavigateToFindCityCoordsScreen = {
                    navController.navigate(FIND_CITY_COORDS_SCREEN)
                },
                onCollectCityCoordinates = {
                    navController.currentBackStackEntry?.savedStateHandle?.
                    get<String>(CITY_COORDINATES_KEY)?.toGeocodeModel()
                }
            )
        }

        composable(route = FIND_CITY_COORDS_SCREEN) {
            FindCityCoordsScreen(
                onNavigateBackWithResult = {
                    navController.previousBackStackEntry?.savedStateHandle?.
                       set(CITY_COORDINATES_KEY, it.toJson())
                    navController.popBackStack()
                },
                onNavigateBack = { navController.popBackStack() },
            )
        }

    }
}