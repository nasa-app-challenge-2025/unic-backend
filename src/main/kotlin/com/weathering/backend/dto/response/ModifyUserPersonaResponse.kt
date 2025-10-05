package com.weathering.backend.dto.response

import java.time.LocalDateTime

data class ModifyUserPersonaResponse(
    val id: Long,
    val userId: Long,
    val personaId: Long,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)
