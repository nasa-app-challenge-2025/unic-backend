package com.weathering.backend.service

import com.weathering.backend.dto.request.DeleteCalendarScheduleRequest
import com.weathering.backend.repository.CalendarScheduleRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class DeleteCalendarScheduleService(
    private val calendarScheduleRepository: CalendarScheduleRepository,
) {
    fun doService(request: DeleteCalendarScheduleRequest) {
        calendarScheduleRepository.deleteById(request.id)
    }
}
