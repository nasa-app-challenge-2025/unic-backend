package com.weathering.backend.service

import com.weathering.backend.domain.CalendarSchedule
import com.weathering.backend.dto.request.CreateCalendarScheduleRequest
import com.weathering.backend.dto.response.CreateCalendarScheduleResponse
import com.weathering.backend.repository.CalendarScheduleRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CreateCalendarScheduleService(
    private val calendarScheduleRepository: CalendarScheduleRepository,
) {
    fun doService(request: CreateCalendarScheduleRequest): CreateCalendarScheduleResponse {
        val domain = CalendarSchedule(
            title = request.title,
            startDate = request.startDate,
            lastDate = request.lastDate
        )

        val saveCalendarSchedule = calendarScheduleRepository.save(domain)

        val response = saveCalendarSchedule.let {
            CreateCalendarScheduleResponse(
                id = it.id,
                title = it.title,
                startDate = it.startDate,
                lastDate = it.lastDate,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt
            )
        }

        return response
    }
}
