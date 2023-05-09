package com.ymlct.weatherapp.presentation.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ymlct.weatherapp.R
import com.ymlct.weatherapp.domain.model.CityWeatherModel
import java.text.SimpleDateFormat
import java.util.Date


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CityWeatherCard(
    modifier: Modifier,
    cityWeather: CityWeatherModel,
    onClick: () -> Unit,
) {

    Card(
        modifier = modifier
            .padding(horizontal = 12.dp)
            .fillMaxWidth(),
        elevation = 6.dp,
        onClick = onClick
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = cityWeather.cityGeocode.cityName,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                val dateTime = SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Date(cityWeather.timestamp))
                Text(
                    text = "${stringResource(id = R.string.last_update)}: $dateTime",
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 24.dp, start = 12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "${stringResource(id = R.string.temperature)}: ${cityWeather.currentWeather.temp}",
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier
                )
                Text(
                    text = "${stringResource(id = R.string.feels_like)}: ${cityWeather.currentWeather.feelsLike}",
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier
                )
                Text(
                    text = "${stringResource(id = R.string.condition)}: ${cityWeather.currentWeather.condition}",
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier
                )
            }
        }

    }
}