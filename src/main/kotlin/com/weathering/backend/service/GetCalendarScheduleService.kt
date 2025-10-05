package com.weathering.backend.service

import com.weathering.backend.dto.request.GetCalendarScheduleRequest
import com.weathering.backend.dto.response.GetCalendarScheduleResponse
import com.weathering.backend.repository.CalendarScheduleRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalTime

@Service
@Transactional(readOnly = true)
class GetCalendarScheduleService(
    val calendarScheduleRepository: CalendarScheduleRepository,
) {
    fun doService(request: GetCalendarScheduleRequest): List<GetCalendarScheduleResponse> {
        val date = request.date

        val startDate = date.atStartOfDay()
        val lastDate = date.atTime(LocalTime.MAX)

        val calendarSchedules = calendarScheduleRepository.findByDate(startDate, lastDate)

        val response = calendarSchedules
            .map {
                GetCalendarScheduleResponse(
                    startDate = it.startDate,
                    lastDate = it.lastDate,
                    title = it.title,
                )
            }

        return response
    }
}
