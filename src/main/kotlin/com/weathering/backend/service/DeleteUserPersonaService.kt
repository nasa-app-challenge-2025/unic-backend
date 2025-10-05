package com.weathering.backend.service

import com.weathering.backend.dto.request.DeleteUserPersonaRequest
import com.weathering.backend.repository.UserPersonaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class DeleteUserPersonaService(
    private val userPersonaRepository: UserPersonaRepository,
) {
    fun doService(request: DeleteUserPersonaRequest) {
        userPersonaRepository.deleteById(request.id)
    }
}
