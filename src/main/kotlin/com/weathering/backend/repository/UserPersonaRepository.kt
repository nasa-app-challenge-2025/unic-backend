package com.weathering.backend.repository

import com.weathering.backend.domain.UserPersona
import org.springframework.data.jpa.repository.JpaRepository

interface UserPersonaRepository : JpaRepository<UserPersona, Long> {
    fun findByUserId(userId: Long): List<UserPersona>
    fun findByUserIdAndPersonaId(userId: Long, personaId: Long): UserPersona
}
