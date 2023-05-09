package com.ymlct.weatherapp.data.model.dto

import com.google.gson.annotations.SerializedName

data class GeocodeDto(

    @SerializedName("name")
    val name: String = "",

    @SerializedName("local_names")
    val localNames: Map<String, String> = emptyMap(),

    @SerializedName("lat")
    val latitude: String = "",


    @SerializedName("lon")
    val longitude: String = "",

    @SerializedName("country")
    val country: String = "",
)

internal const val EN = "en"
internal const val RU = "ru"
