package com.weathering.backend.service

import com.weathering.backend.dto.request.GetUserPersonaRequest
import com.weathering.backend.dto.response.GetUserPersonaResponse
import com.weathering.backend.repository.UserPersonaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class GetUserPersonaService(
    private val userPersonaRepository: UserPersonaRepository,
) {
    fun doService(request: GetUserPersonaRequest): List<GetUserPersonaResponse> {
        val userPersonas = userPersonaRepository.findByUserId(request.userId)

        val response = userPersonas.map {
            GetUserPersonaResponse(
                id = it.id,
                userId = it.userId,
                personaId = it.personaId,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt
            )
        }

        return response
    }
}
