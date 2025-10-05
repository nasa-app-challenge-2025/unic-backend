package com.weathering.backend.service

import com.weathering.backend.domain.UserLocation
import com.weathering.backend.dto.request.CreateUserLocationRequest
import com.weathering.backend.dto.response.CreateUserLocationResponse
import com.weathering.backend.repository.UserLocationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CreateUserLocationService(
    private val userLocationRepository: UserLocationRepository,
) {
    fun doService(request: CreateUserLocationRequest): CreateUserLocationResponse {
        val domain = UserLocation(
            userId = request.userId,
            lat = request.lat,
            lon = request.lon
        )

        val createUserLocation = userLocationRepository.save(domain)

        val response = createUserLocation.let {
            CreateUserLocationResponse(
                id = it.id,
                userId = it.userId,
                lat = it.lat,
                lon = it.lon,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt
            )
        }

        return response
    }
}
