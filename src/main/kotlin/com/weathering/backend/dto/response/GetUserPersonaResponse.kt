package com.weathering.backend.dto.response

import java.time.LocalDateTime

data class GetUserPersonaResponse(
    val id: Long,
    val userId: Long,
    val personaId: Long,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)
