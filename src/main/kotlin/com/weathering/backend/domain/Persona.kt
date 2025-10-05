package com.weathering.backend.domain

import com.weathering.backend.domain.enum.PersonaEnumType
import jakarta.persistence.*

@Entity
@Table(name = "personas")
data class Persona(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Enumerated(EnumType.STRING)
    val persona: PersonaEnumType,
) : BaseEntity()
