package com.weathering.backend.service

import com.weathering.backend.dto.request.DeleteUserLocationRequest
import com.weathering.backend.repository.UserLocationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class DeleteUserLocationService(
    private val userLocationRepository: UserLocationRepository
) {
    fun doService(request: DeleteUserLocationRequest) {
        userLocationRepository.deleteById(request.id)
    }
}
