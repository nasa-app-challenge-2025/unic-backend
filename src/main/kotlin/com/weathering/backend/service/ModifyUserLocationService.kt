package com.weathering.backend.service

import com.weathering.backend.domain.UserLocation
import com.weathering.backend.dto.request.ModifyUserLocationRequest
import com.weathering.backend.dto.response.ModifyUserLocationResponse
import com.weathering.backend.repository.UserLocationRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.Exception

@Service
@Transactional
class ModifyUserLocationService(
    private val userLocationRepository: UserLocationRepository
) {
    fun doService(request: ModifyUserLocationRequest): ModifyUserLocationResponse {
        val userLocation = userLocationRepository.findByIdOrNull(request.id)
            ?: throw Exception("request {id} entity is null")

        val domain = userLocation.let {
            UserLocation(
                id = it.id,
                userId = it.userId,
                lat = request.lat,
                lon = request.lon,
            )
        }

        val modifyUserLocation = userLocationRepository.save(domain)

        val response = modifyUserLocation.let {
            ModifyUserLocationResponse(
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
