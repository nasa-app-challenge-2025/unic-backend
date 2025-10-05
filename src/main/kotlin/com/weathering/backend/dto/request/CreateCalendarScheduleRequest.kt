package com.weathering.backend.dto.request

import java.time.LocalDateTime

data class CreateCalendarScheduleRequest(
    val title: String,
    val startDate: LocalDateTime,
    val lastDate: LocalDateTime,
)
