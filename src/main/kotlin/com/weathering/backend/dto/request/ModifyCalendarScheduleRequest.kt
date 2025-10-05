package com.weathering.backend.dto.request

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDateTime

data class ModifyCalendarScheduleRequest(
    val title: String,
    val startDate: LocalDateTime,
    val lastDate: LocalDateTime
) {
    @JsonIgnore
    var id: Long = 0
}
