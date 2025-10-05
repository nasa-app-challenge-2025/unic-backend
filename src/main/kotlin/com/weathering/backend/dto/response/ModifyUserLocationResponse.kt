package com.weathering.backend.dto.response

import java.time.LocalDateTime

data class ModifyUserLocationResponse(
    val id: Long,
    val userId: Long,
    val lat: Double,
    val lon: Double,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)
