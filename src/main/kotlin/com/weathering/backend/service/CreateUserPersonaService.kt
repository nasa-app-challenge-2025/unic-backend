package com.weathering.backend.service

import com.weathering.backend.domain.UserPersona
import com.weathering.backend.dto.request.CreateUserPersonaRequest
import com.weathering.backend.dto.response.CreateUserPersonaResponse
import com.weathering.backend.repository.UserPersonaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CreateUserPersonaService(
    private val userPersonaRepository: UserPersonaRepository,
) {
    fun doService(request: CreateUserPersonaRequest): CreateUserPersonaResponse {
        val domain = UserPersona(
            userId = request.userId,
            personaId = request.personaId
        )

        val createUserPersona = userPersonaRepository.save(domain)

        val response = createUserPersona.let {
            CreateUserPersonaResponse(
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
