package com.weathering.backend.repository

import com.weathering.backend.dto.response.GetOpenWeatherResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam


@FeignClient(name = "weather-service", url = "http://api.openweathermap.org")
interface OpenWeatherMapAPI {
    @GetMapping("/data/2.5/forecast")
    fun getWeather5Days(
        @RequestParam("lat") lat: Double,
        @RequestParam("lon") lon: Double,
        @RequestParam("appid") appid: String,
    ): GetOpenWeatherResponse

    @GetMapping("/data/2.5/forecast")
    fun getWeatherByCityName(
        @RequestParam("q") cityName: String,
        @RequestParam("appid") appid: String,
    ): GetOpenWeatherResponse
}
