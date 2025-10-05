package com.weathering.backend.dto.response

import com.weathering.backend.domain.enum.PersonaEnumType

data class GetPersonaResponse(
    val id: Long,
    val persona: PersonaEnumType,
)
