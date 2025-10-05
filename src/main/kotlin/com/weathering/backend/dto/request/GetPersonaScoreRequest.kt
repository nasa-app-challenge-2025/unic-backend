package com.weathering.backend.dto.request

import com.fasterxml.jackson.annotation.JsonIgnore
import com.weathering.backend.domain.enum.PersonaEnumType

data class GetPersonaScoreRequest(
    val pm25_24h: Double = 32.0,
    val pm10_24h: Double = 55.0,
    val o3_8h: Double = 68.0,
    val no2_1h: Double = 35.0,
    val no2_24h: Double = 18.0,
    val co_8h: Double = 0.9,
    val so2: Double = 14.0,
    val hcho: Double = 0.02,
    val pollen_level: String = "High",
    val heat: Boolean = true,
    val cold: Boolean = false,
    val dP: Boolean = false,
    val storm: Boolean = false,
) {
    @JsonIgnore
    var persona: PersonaEnumType = PersonaEnumType.COPD
}
