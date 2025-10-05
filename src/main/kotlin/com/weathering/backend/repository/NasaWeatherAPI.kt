package com.weathering.backend.repository

import com.weathering.backend.dto.response.GetMarsWeatherResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "nasa-weather-service", url = "http://api.nasa.gov")
interface NasaWeatherAPI {
    @GetMapping("/insight_weather")
    fun getMarsWeather(
        @RequestParam("api_key") apiKey: String,
        @RequestParam("feedtype") feedtype: String,
        @RequestParam("ver") ver: String,
    ): GetMarsWeatherResponse
}
