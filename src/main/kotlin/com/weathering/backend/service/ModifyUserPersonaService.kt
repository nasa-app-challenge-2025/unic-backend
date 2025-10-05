package com.weathering.backend.service

import com.weathering.backend.domain.UserPersona
import com.weathering.backend.dto.request.ModifyUserPersonaRequest
import com.weathering.backend.dto.response.ModifyUserPersonaResponse
import com.weathering.backend.repository.UserPersonaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ModifyUserPersonaService(
    val userPersonaRepository: UserPersonaRepository,
) {
    fun doService(request: ModifyUserPersonaRequest): ModifyUserPersonaResponse {
        val userPersona = userPersonaRepository.findByIdOrNull(request.id)
            ?: throw Exception("request {id} is null")

        val domain = userPersona.let {
            UserPersona(
                id = it.id,
                userId = it.userId,
                personaId = request.personaId,
            )
        }

        val modifyUserPersona = userPersonaRepository.save(domain)

        val response = modifyUserPersona.let {
            ModifyUserPersonaResponse(
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
