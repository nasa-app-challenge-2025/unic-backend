package com.weathering.backend.dto.response

import java.time.LocalDateTime

data class ModifyCalendarScheduleResponse(
    val id: Long,
    val title: String,
    val startDate: LocalDateTime,
    val lastDate: LocalDateTime,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)
