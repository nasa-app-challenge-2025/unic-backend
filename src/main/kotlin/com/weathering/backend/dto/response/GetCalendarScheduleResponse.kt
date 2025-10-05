package com.weathering.backend.dto.response

import java.time.LocalDateTime

data class GetCalendarScheduleResponse(
    val startDate: LocalDateTime,
    val lastDate: LocalDateTime,
    val title: String,
)
