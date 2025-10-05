package com.weathering.backend.service

import com.weathering.backend.dto.request.GetOpenWeatherByLatAndLonRequest
import com.weathering.backend.dto.response.GetOpenWeatherResponse
import com.weathering.backend.repository.OpenWeatherMapAPI
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class GetOpenWeatherByLatAndLonService(
    private val openWeatherMapAPI: OpenWeatherMapAPI,
    @Value("\${weather.open-api.api-key}") private val apiKey: String,
) {
    fun doService(request: GetOpenWeatherByLatAndLonRequest): GetOpenWeatherResponse {
        return openWeatherMapAPI.getWeather5Days(lat = request.lat, lon = request.lon, appid = apiKey)
    }
}
