package com.weathering.backend.domain

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "calendar_schedules")
data class CalendarSchedule(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val title: String,
    val startDate: LocalDateTime,
    val lastDate: LocalDateTime,
) : BaseEntity()
