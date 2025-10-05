package com.weathering.backend.controller

import com.weathering.backend.domain.enum.PersonaEnumType
import com.weathering.backend.dto.request.*
import com.weathering.backend.dto.response.*
import com.weathering.backend.service.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/public/v1/personas")
class PersonaController(
    private val createUserPersonaService: CreateUserPersonaService,
    private val getPersonaListService: GetPersonaListService,
    private val getPersonaService: GetPersonaService,
    private val getPersonaScoreListService: GetPersonaScoreListService,
    private val getPersonaScoreService: GetPersonaScoreService,
    private val getUserPersonaService: GetUserPersonaService,
    private val modifyUserPersonaService: ModifyUserPersonaService,
    private val deleteUserPersonaService: DeleteUserPersonaService,
) {
    @PostMapping
    fun createUserPersona(
        @RequestBody request: CreateUserPersonaRequest
    ): CreateUserPersonaResponse {
        val response = createUserPersonaService.doService(request)
        return response
    }

    @GetMapping
    fun getPersonas(): List<GetPersonaResponse> {
        val response = getPersonaListService.doService()
        return response
    }

    @GetMapping("/{personaId}")
    fun getPersonaById(
        @PathVariable("personaId") personaId: Long
    ): GetPersonaResponse? {
        val request = GetPersonaRequest(personaId = personaId)
        val response = getPersonaService.doService(request = request)
        return response
    }

    @GetMapping("/scores")
    fun getPersonaScores(
        @RequestBody request: GetPersonaScoreRequest,
    ): List<GetPersonaScoreResponse> {
        val response = getPersonaScoreListService.doService(request = request)
        return response
    }

    @GetMapping("/score")
    fun getPersonaScore(
        @RequestParam("persona") persona: PersonaEnumType,
        @RequestBody request: GetPersonaScoreRequest,
    ): GetPersonaScoreResponse {
        request.persona = persona
        val response = getPersonaScoreService.doService(request = request)
        return response
    }

    @GetMapping("/users/{userId}")
    fun getUserPersona(
        @PathVariable("userId") userId: Long,
    ): List<GetUserPersonaResponse> {
        val request = GetUserPersonaRequest(userId = userId)
        val response = getUserPersonaService.doService(request = request)
        return response
    }

    @PutMapping("/{personaId}/user-persona/{id}")
    fun modifyUserPersona(
        @PathVariable("personaId") personaId: Long,
        @PathVariable("id") id: Long,
    ): ModifyUserPersonaResponse {
        val request = ModifyUserPersonaRequest(personaId = personaId, id = id)
        val response = modifyUserPersonaService.doService(request)
        return response
    }

    @DeleteMapping("/user-persona/{id}")
    fun deleteUserPersona(
        @PathVariable("id") id: Long,
    ) {
        val request = DeleteUserPersonaRequest(id = id)
        deleteUserPersonaService.doService(request)
    }
}
