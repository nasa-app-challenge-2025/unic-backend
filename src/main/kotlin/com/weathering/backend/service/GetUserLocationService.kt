package com.weathering.backend.service

import com.weathering.backend.dto.request.GetUserLocationRequest
import com.weathering.backend.dto.response.GetUserLocationResponse
import com.weathering.backend.repository.UserLocationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class GetUserLocationService(
    private val userLocationRepository: UserLocationRepository,
) {
    fun doService(request: GetUserLocationRequest): List<GetUserLocationResponse> {
        val userLocations = userLocationRepository.findByUserId(request.userId)

        val response = userLocations.map {
            GetUserLocationResponse(
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
