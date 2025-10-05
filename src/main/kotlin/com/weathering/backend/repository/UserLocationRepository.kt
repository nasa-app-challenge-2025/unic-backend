package com.weathering.backend.repository

import com.weathering.backend.domain.UserLocation
import org.springframework.data.jpa.repository.JpaRepository

interface UserLocationRepository : JpaRepository<UserLocation, Long> {
    fun findByUserId(userId: Long): List<UserLocation>
}
