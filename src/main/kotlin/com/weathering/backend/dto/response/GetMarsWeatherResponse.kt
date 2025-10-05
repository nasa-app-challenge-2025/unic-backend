package com.weathering.backend.dto.response

data class GetMarsWeatherResponse(
    val sol: String? = null,                 // 화성일 (Sol)
    val at: AtmosphericData? = null,         // 온도 데이터
    val hws: WindData? = null,               // 풍속 데이터
    val pre: PressureData? = null,           // 기압 데이터
    val season: String? = null,              // 화성 계절
    val northern_season: String? = null,     // 북반구 계절
    val southern_season: String? = null,     // 남반구 계절
    val first_utc: String? = null,           // 관측 시작 UTC
    val last_utc: String? = null,            // 관측 종료 UTC
    val month_ordinal: Int? = null           // 화성 월(순서)
)

data class AtmosphericData(
    val av: Double? = null,  // 평균 기온 (°C)
    val ct: Int? = null,     // 데이터 카운트
    val mn: Double? = null,  // 최저 기온
    val mx: Double? = null   // 최고 기온
)

data class WindData(
    val av: Double? = null,  // 평균 풍속 (m/s)
    val ct: Int? = null,     // 데이터 카운트
    val mn: Double? = null,  // 최저 풍속
    val mx: Double? = null   // 최고 풍속
)

data class PressureData(
    val av: Double? = null,  // 평균 기압 (Pa)
    val ct: Int? = null,     // 데이터 카운트
    val mn: Double? = null,  // 최저 기압
    val mx: Double? = null   // 최고 기압
)
