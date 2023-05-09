package com.ymlct.weatherapp.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ymlct.weatherapp.domain.model.CityGeocodeModel
import com.ymlct.weatherapp.presentation.ui.components.CityCoordCard
import com.ymlct.weatherapp.presentation.viewmodel.FindCityByCoordsViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FindCityCoordsScreen(
    onNavigateBackWithResult: (CityGeocodeModel) -> Unit,
    onNavigateBack: () -> Unit,
) {

    val viewModel: FindCityByCoordsViewModel = hiltViewModel()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {

            var isSearchVisible by remember { mutableStateOf(false) }

            TopAppBar(
                elevation = 5.dp,
            ) {

                var query by remember { mutableStateOf("") }

                if (isSearchVisible) {

                    // cancel button
                    IconButton(
                        onClick = {
                            isSearchVisible = false
                            query = ""
                        }
                    ) { Icon(imageVector = Icons.Filled.Clear, contentDescription = null) }

                    BasicTextField(
                        value = query,
                        onValueChange = { query = it },
                        singleLine = true,
                        modifier = Modifier.weight(1f, true)
                    )

                    // accept button
                    IconButton(
                        onClick = {
                            if (query.isNotBlank())
                                viewModel.requestCoordsForCityName(query)
                            isSearchVisible = false
                            query = ""
                        }
                    ) { Icon(imageVector = Icons.Filled.Done, contentDescription = null) }

                } else {
                    // back button
                    IconButton(onClick = onNavigateBack) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                    }
                    Spacer(Modifier.weight(1f, true))

                    // search button
                    IconButton(onClick = { isSearchVisible = true }) {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = null)
                    }
                }
            }
        }
    ) {

        val list by viewModel.coordsForCityNameList.collectAsStateWithLifecycle()

        LazyColumn(modifier = Modifier.padding(top = 8.dp).fillMaxSize()) {
            items (count = list.size) {
                CityCoordCard(
                    modifier = Modifier.padding(top = 12.dp),
                    city = list[it],
                    onClick = {
                        onNavigateBackWithResult(
                            list[it]
                        )
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    FindCityCoordsScreen(
        onNavigateBackWithResult = { },
        onNavigateBack = { },
    )
}