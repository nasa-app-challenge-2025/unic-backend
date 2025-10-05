package com.weathering.backend.service

import com.weathering.backend.dto.request.GetPersonaRequest
import com.weathering.backend.dto.response.GetPersonaResponse
import com.weathering.backend.repository.PersonaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class GetPersonaService(
    private val personaRepository: PersonaRepository
) {
    fun doService(request: GetPersonaRequest): GetPersonaResponse? {
        val persona = personaRepository.findByIdOrNull(request.personaId)

        val response = persona?.let {
            GetPersonaResponse(
                id = it.id,
                persona = it.persona
            )
        }

        return response
    }
}
