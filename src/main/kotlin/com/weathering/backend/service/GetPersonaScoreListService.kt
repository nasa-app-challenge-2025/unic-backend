package com.weathering.backend.service

import com.weathering.backend.dto.request.GetPersonaScoreRequest
import com.weathering.backend.dto.response.GetPersonaScoreResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class GetPersonaScoreListService(
    private val personaScoreService: PersonaScoreService,
) {
    fun doService(request: GetPersonaScoreRequest): List<GetPersonaScoreResponse> {
        return personaScoreService.calculateAllScores(input = request)
    }
}
