package com.weathering.backend.dto.response

import com.weathering.backend.domain.enum.PersonaEnumType

data class GetPersonaScoreResponse(
    val persona: PersonaEnumType,
    val score: Double,
    val level: String
)
