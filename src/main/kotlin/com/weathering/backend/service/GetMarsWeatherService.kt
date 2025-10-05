package com.weathering.backend.service

import com.weathering.backend.dto.response.GetMarsWeatherResponse
import com.weathering.backend.repository.NasaWeatherAPI
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class GetMarsWeatherService(
    private val nasaWeatherAPI: NasaWeatherAPI,
    @Value("\${nasa.weather.open-api.api-key}") private val apiKey: String,

    ) {
    fun doService(): GetMarsWeatherResponse {
        return nasaWeatherAPI.getMarsWeather(apiKey = apiKey, feedtype = "json", ver = "1.0")
    }
}
