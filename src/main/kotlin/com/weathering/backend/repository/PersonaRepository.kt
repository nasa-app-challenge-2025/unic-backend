package com.weathering.backend.repository

import com.weathering.backend.domain.Persona
import org.springframework.data.jpa.repository.JpaRepository

interface PersonaRepository : JpaRepository<Persona, Long> {
}
