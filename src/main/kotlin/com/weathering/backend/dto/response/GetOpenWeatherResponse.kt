package com.weathering.backend.dto.response

import io.swagger.v3.oas.annotations.media.Schema

data class GetOpenWeatherResponse(
    @Schema(description = "내부 파라미터")
    val cod: String,

    @Schema(description = "내부 파라미터")
    val message: String,

    @Schema(description = "API 응답에 포함된 타임스탬프 수")
    val cntA: Int,

    @Schema(description = "예보 데이터 리스트")
    val list: List<ForecastItem>,

    @Schema(description = "도시 정보")
    val city: City
)

data class ForecastItem(
    @Schema(description = "예보 데이터 시간 (Unix, UTC)")
    val dt: Long,

    @Schema(description = "주요 날씨 정보")
    val main: Main,

    @Schema(description = "날씨 조건")
    val weather: List<Weather>,

    @Schema(description = "구름량 (%)")
    val clouds: Clouds,

    @Schema(description = "바람 정보")
    val wind: Wind,

    @Schema(description = "가시거리 (최대 10km)", nullable = true)
    val visibility: Int? = null,

    @Schema(description = "강수 확률 (0.0 ~ 1.0)", nullable = true)
    val pop: Double? = null,

    @Schema(description = "최근 3시간 강수량 (mm)", nullable = true)
    val rain: Rain? = null,

    @Schema(description = "최근 3시간 적설량 (mm)", nullable = true)
    val snow: Snow? = null,

    @Schema(description = "시간대 (n - 밤, d - 낮)")
    val sys: Sys,

    @Schema(description = "예보 데이터 시간 (ISO, UTC)")
    val dt_txt: String
)

data class Main(
    @Schema(description = "현재 기온 (기본 단위: 켈빈, 섭씨, 화씨 선택 가능)")
    val temp: Double,

    @Schema(description = "체감 온도")
    val feels_like: Double,

    @Schema(description = "최저 기온")
    val temp_min: Double,

    @Schema(description = "최고 기온")
    val temp_max: Double,

    @Schema(description = "해수면 기준 기압 (hPa)")
    val pressure: Int,

    @Schema(description = "해수면 기압 (hPa)", nullable = true)
    val sea_level: Int? = null,

    @Schema(description = "지면 기압 (hPa)", nullable = true)
    val grnd_level: Int? = null,

    @Schema(description = "습도 (%)")
    val humidity: Int,

    @Schema(description = "내부 파라미터", nullable = true)
    val temp_kf: Double? = null
)

data class Weather(
    @Schema(description = "날씨 상태 ID")
    val id: Int,

    @Schema(description = "날씨 그룹 (비, 눈, 구름 등)")
    val main: String,

    @Schema(description = "상세 날씨 설명")
    val description: String,

    @Schema(description = "날씨 아이콘 ID")
    val icon: String
)

data class Clouds(
    @Schema(description = "구름량 (%)")
    val all: Int
)

data class Wind(
    @Schema(description = "풍속 (기본 단위: m/s)")
    val speed: Double,

    @Schema(description = "풍향 (도, 기상학적)")
    val deg: Int,

    @Schema(description = "돌풍 (기본 단위: m/s)", nullable = true)
    val gust: Double? = null
)

data class Rain(
    @Schema(description = "최근 3시간 강수량 (mm)")
    val `3h`: Double
)

data class Snow(
    @Schema(description = "최근 3시간 적설량 (mm)")
    val `3h`: Double
)

data class Sys(
    @Schema(description = "시간대 (n - 밤, d - 낮)")
    val pod: String
)

data class City(
    @Schema(description = "도시 ID")
    val id: Long,

    @Schema(description = "도시 이름")
    val name: String,

    @Schema(description = "도시 좌표")
    val coord: Coord,

    @Schema(description = "국가 코드 (GB, JP 등)")
    val country: String,

    @Schema(description = "도시 인구", nullable = true)
    val population: Long? = null,

    @Schema(description = "UTC로부터의 시간 차이 (초)")
    val timezone: Int,

    @Schema(description = "일출 시간 (Unix, UTC)")
    val sunrise: Long,

    @Schema(description = "일몰 시간 (Unix, UTC)")
    val sunset: Long
)

data class Coord(
    @Schema(description = "위도")
    val lat: Double,

    @Schema(description = "경도")
    val lon: Double
)
