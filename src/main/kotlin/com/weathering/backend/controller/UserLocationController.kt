package com.weathering.backend.controller

import com.weathering.backend.dto.request.CreateUserLocationRequest
import com.weathering.backend.dto.request.DeleteUserLocationRequest
import com.weathering.backend.dto.request.GetUserLocationRequest
import com.weathering.backend.dto.request.ModifyUserLocationRequest
import com.weathering.backend.dto.response.CreateUserLocationResponse
import com.weathering.backend.dto.response.GetUserLocationResponse
import com.weathering.backend.dto.response.ModifyUserLocationResponse
import com.weathering.backend.service.CreateUserLocationService
import com.weathering.backend.service.DeleteUserLocationService
import com.weathering.backend.service.GetUserLocationService
import com.weathering.backend.service.ModifyUserLocationService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/public/v1/user-locations")
class UserLocationController(
    private val createUserLocationService: CreateUserLocationService,
    private val getUserLocationService: GetUserLocationService,
    private val modifyUserLocationService: ModifyUserLocationService,
    private val deleteUserLocationService: DeleteUserLocationService,
) {
    @PostMapping
    fun createUserLocation(
        @RequestBody request: CreateUserLocationRequest,
    ): CreateUserLocationResponse {
        val response = createUserLocationService.doService(request)
        return response
    }

    @GetMapping("/users/{userId}")
    fun getUserLocation(
        @PathVariable("userId") userId: Long
    ): List<GetUserLocationResponse> {
        val request = GetUserLocationRequest(userId = userId)
        val response = getUserLocationService.doService(request = request)
        return response
    }

    @PutMapping("/{id}")
    fun modifyUserLocation(
        @PathVariable("id") id: Long,
        @RequestBody request: ModifyUserLocationRequest
    ): ModifyUserLocationResponse {
        request.id = id
        val response = modifyUserLocationService.doService(request = request)
        return response
    }

    @DeleteMapping("/{id}")
    fun deleteUserLocation(
        @PathVariable("id") id: Long,
    ) {
        val request = DeleteUserLocationRequest(id = id)
        deleteUserLocationService.doService(request = request)
    }
}
