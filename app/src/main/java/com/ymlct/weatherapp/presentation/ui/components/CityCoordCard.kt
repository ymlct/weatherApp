package com.ymlct.weatherapp.presentation.ui.components

import android.annotation.SuppressLint
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ymlct.weatherapp.domain.model.CityGeocodeModel

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CityCoordCard(
    modifier: Modifier,
    city: CityGeocodeModel,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .padding(horizontal = 12.dp)
            .fillMaxWidth(),
        elevation = 6.dp,
        onClick = onClick
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = city.cityName,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(start = 8.dp, end = 4.dp)
                )
                Text(
                    text = "(${city.countryCode.uppercase()})",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 24.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "(latitude: ${city.latitude}, ",
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(start = 8.dp, end = 4.dp)
                )
                Text(
                    text = "longitude: ${city.longitude})",
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }

    }
}

@Preview(widthDp = 360)
@Composable
private fun Preview() {
    val city = CityGeocodeModel(
        cityName = "Kemerovo",
        countryCode = "ru",
        latitude = "55.33333",
        longitude = "86.08333",
    )
    CityCoordCard(
        modifier = Modifier,
        city = city,
        onClick = { }
    )
}