package com.weathering.backend.domain

import jakarta.persistence.*

@Entity
@Table(name = "user_locations")
data class UserLocation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val userId: Long,
    val lat: Double,
    val lon: Double,
): BaseEntity()
