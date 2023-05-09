package com.ymlct.weatherapp.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.*

// custom app dark color palette
private val AppDarkColorPalette = ColorsApp(
    material = darkColors(),
    customAppColor = Purple700
)

// custom app light color palette
private val AppLightColorPalette = ColorsApp(
    material = lightColors(),
    customAppColor = Purple200
)

// defines global variable for the composable tree
// initial value - `AppLightColorPalette`, - will be overridden
private val LocalAppColors = staticCompositionLocalOf { AppLightColorPalette }

// property extension provides current value of global variable for the composable tree
// represented by concrete version of ColorsApp
val MaterialTheme.colorsApp: ColorsApp
    @Composable
    @ReadOnlyComposable
    get() = LocalAppColors.current

@Composable
fun WeatherAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorsApp = if (darkTheme) {
        AppDarkColorPalette
    } else {
        AppLightColorPalette
    }

    // makes concrete version of ColorsApp be available through the whole composable tree
    // without passing it as a parameter
    CompositionLocalProvider(
        LocalAppColors provides colorsApp
    ) {
        MaterialTheme(
            colors = colorsApp.material,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}