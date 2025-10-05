package com.weathering.backend.service

import com.weathering.backend.dto.response.GetPersonaResponse
import com.weathering.backend.repository.PersonaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class GetPersonaListService(
    private val personaRepository: PersonaRepository,
) {
    fun doService(): List<GetPersonaResponse> {
        val personas = personaRepository.findAll()

        val response = personas.map {
            GetPersonaResponse(
                id = it.id,
                persona = it.persona,
            )
        }

        return response
    }
}
