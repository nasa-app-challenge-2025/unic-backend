package com.weathering.backend.domain

import jakarta.persistence.*

@Entity
@Table(name = "user_personas")
data class UserPersona(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val userId: Long,
    val personaId: Long,
): BaseEntity()
