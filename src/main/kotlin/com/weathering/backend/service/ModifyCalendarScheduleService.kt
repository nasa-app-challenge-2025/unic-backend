package com.weathering.backend.service

import com.weathering.backend.domain.CalendarSchedule
import com.weathering.backend.dto.request.ModifyCalendarScheduleRequest
import com.weathering.backend.dto.response.ModifyCalendarScheduleResponse
import com.weathering.backend.repository.CalendarScheduleRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ModifyCalendarScheduleService(
    private val calendarScheduleRepository: CalendarScheduleRepository,
) {
    fun doService(request: ModifyCalendarScheduleRequest): ModifyCalendarScheduleResponse {
        val domain = CalendarSchedule(
            id = request.id,
            title = request.title,
            startDate = request.startDate,
            lastDate = request.lastDate
        )

        val saveCalendarSchedule = calendarScheduleRepository.save(domain)

        val response = saveCalendarSchedule.let {
            ModifyCalendarScheduleResponse(
                id = it.id,
                title = it.title,
                startDate = it.startDate,
                lastDate = it.lastDate,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt,
            )
        }

        return response
    }
}
