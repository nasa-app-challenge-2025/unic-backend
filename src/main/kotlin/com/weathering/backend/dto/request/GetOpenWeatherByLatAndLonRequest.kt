package com.weathering.backend.dto.request

data class GetOpenWeatherByLatAndLonRequest (
    val lat: Double,
    val lon: Double,
)
