package com.weathering.backend.dto.request

import com.fasterxml.jackson.annotation.JsonIgnore

data class ModifyUserLocationRequest(
    val lat: Double,
    val lon: Double
) {
    @JsonIgnore
    var id: Long = 0
}
