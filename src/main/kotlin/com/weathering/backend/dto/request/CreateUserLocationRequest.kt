package com.weathering.backend.dto.request

data class CreateUserLocationRequest(
    val userId: Long,
    val lat: Double,
    val lon: Double,
)
