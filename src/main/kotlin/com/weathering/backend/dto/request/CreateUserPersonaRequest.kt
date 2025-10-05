package com.weathering.backend.dto.request

data class CreateUserPersonaRequest(
    val userId: Long,
    val personaId: Long,
)
