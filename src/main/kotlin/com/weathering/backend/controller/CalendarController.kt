package com.weathering.backend.controller

import com.weathering.backend.dto.request.CreateCalendarScheduleRequest
import com.weathering.backend.dto.request.DeleteCalendarScheduleRequest
import com.weathering.backend.dto.request.GetCalendarScheduleRequest
import com.weathering.backend.dto.request.ModifyCalendarScheduleRequest
import com.weathering.backend.dto.response.CreateCalendarScheduleResponse
import com.weathering.backend.dto.response.GetCalendarScheduleResponse
import com.weathering.backend.service.CreateCalendarScheduleService
import com.weathering.backend.service.GetCalendarScheduleService
import com.weathering.backend.dto.response.ModifyCalendarScheduleResponse
import com.weathering.backend.service.DeleteCalendarScheduleService
import com.weathering.backend.service.ModifyCalendarScheduleService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/public/v1/calendars")
class CalendarController(
    private val createCalendarScheduleService: CreateCalendarScheduleService,
    private val getCalendarScheduleService: GetCalendarScheduleService,
    private val modifyCalendarScheduleService: ModifyCalendarScheduleService,
    private val deleteCalendarScheduleService: DeleteCalendarScheduleService,
) {
    @PostMapping
    fun createCalendarSchedule(
        @RequestBody request: CreateCalendarScheduleRequest
    ): CreateCalendarScheduleResponse {
        val response = createCalendarScheduleService.doService(request = request)
        return response
    }

    @GetMapping("/schedules")
    fun getCalendarSchedule(
        @RequestParam("date") date: LocalDate,
    ): List<GetCalendarScheduleResponse> {
        val request = GetCalendarScheduleRequest(date = date)
        val response = getCalendarScheduleService.doService(request = request)

        return response
    }

    @PutMapping("/{id}")
    fun modifyCalendarSchedule(
        @PathVariable("id") id: Long,
        @RequestBody request: ModifyCalendarScheduleRequest
    ): ModifyCalendarScheduleResponse {
        request.id = id
        val response = modifyCalendarScheduleService.doService(request = request)
        return response
    }

    @DeleteMapping("/{id}")
    fun deleteCalendarSchedule(
        @PathVariable("id") id: Long,
    ) {
        val request = DeleteCalendarScheduleRequest(id = id)
        deleteCalendarScheduleService.doService(request = request)
    }
}
