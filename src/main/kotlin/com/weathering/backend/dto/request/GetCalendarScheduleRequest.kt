package com.weathering.backend.dto.request

import java.time.LocalDate

data class GetCalendarScheduleRequest(
    val date: LocalDate
)
