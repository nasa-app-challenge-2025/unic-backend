package com.weathering.backend.repository

import com.weathering.backend.domain.CalendarSchedule
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime

interface CalendarScheduleRepository : JpaRepository<CalendarSchedule, Long> {
    @Query("""
    SELECT c FROM CalendarSchedule c
    WHERE c.startDate <= :endOfDay AND c.lastDate >= :startOfDay
""")
    fun findByDate(
        startOfDay: LocalDateTime,
        endOfDay: LocalDateTime
    ): List<CalendarSchedule>
}
