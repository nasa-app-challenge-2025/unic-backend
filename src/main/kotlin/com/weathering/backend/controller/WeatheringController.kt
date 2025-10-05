package com.weathering.backend.controller

import com.weathering.backend.dto.request.GetOpenWeatherByCityNameRequest
import com.weathering.backend.dto.request.GetOpenWeatherByLatAndLonRequest
import com.weathering.backend.dto.response.GetMarsWeatherResponse
import com.weathering.backend.dto.response.GetOpenWeatherResponse
import com.weathering.backend.service.GetMarsWeatherService
import com.weathering.backend.service.GetOpenWeatherByCityNameService
import com.weathering.backend.service.GetOpenWeatherByLatAndLonService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/public/v1/weathering")
class WeatheringController(
    private val getOpenWeatherByLatAndLonService: GetOpenWeatherByLatAndLonService,
    private val getOpenWeatherByCityNameService: GetOpenWeatherByCityNameService,
    private val getMarsWeatherService: GetMarsWeatherService,
) {
    @GetMapping("/lat-and-lon")
    fun getOpenWeatherByLatAndLon(
        @RequestParam("lat") lat: Double,
        @RequestParam("lon") lon: Double,
    ): GetOpenWeatherResponse {
        val request = GetOpenWeatherByLatAndLonRequest(lat = lat, lon = lon)
        val response = getOpenWeatherByLatAndLonService.doService(request = request)
        return response
    }

    @GetMapping("/city-name")
    fun getOpenWeatherByCityName(
        @RequestParam("cityName") cityName: String,
    ): GetOpenWeatherResponse {
        val request = GetOpenWeatherByCityNameRequest(cityName = cityName)
        val response = getOpenWeatherByCityNameService.doService(request = request)
        return response
    }

    @GetMapping("/mars")
    fun getMarsWeather(): GetMarsWeatherResponse {
        val response = getMarsWeatherService.doService()
        return response
    }
}
