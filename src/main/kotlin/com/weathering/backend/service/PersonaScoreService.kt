package com.weathering.backend.service

import com.weathering.backend.domain.enum.PersonaEnumType
import com.weathering.backend.dto.request.GetPersonaScoreRequest
import com.weathering.backend.dto.response.GetPersonaScoreResponse
import org.springframework.stereotype.Service
import kotlin.math.max
import kotlin.math.pow

@Service
class PersonaScoreService {

    private val alpha = 0.6

    // 질환별 고정 multiplier
    private val activityMultDefault = mapOf(
        PersonaEnumType.ASTHMA to 1.05,
        PersonaEnumType.COPD to 1.10,
        PersonaEnumType.RHINITIS to 1.05,
        PersonaEnumType.CONJUNCTIVITIS to 1.05,
        PersonaEnumType.CARDIO to 1.10,
        PersonaEnumType.MIGRAINE to 1.05,
        PersonaEnumType.MARS to 1.05,
    )

    private val contextMultDefault = mapOf(
        PersonaEnumType.ASTHMA to 0.95,
        PersonaEnumType.COPD to 0.90,
        PersonaEnumType.RHINITIS to 0.95,
        PersonaEnumType.CONJUNCTIVITIS to 0.95,
        PersonaEnumType.CARDIO to 0.90,
        PersonaEnumType.MIGRAINE to 0.95,
        PersonaEnumType.MARS to 0.95
    )

    // 질환별 factor weights
    private val factorWeights = mapOf(
        PersonaEnumType.ASTHMA to listOf(1.0,0.6,1.0,0.9,0.3,0.7,0.6,0.8,0.3,0.3,0.1,0.2),
        PersonaEnumType.COPD to listOf(1.0,0.7,0.9,0.9,0.4,0.7,0.5,0.4,0.5,0.5,0.2,0.3),
        PersonaEnumType.RHINITIS to listOf(0.7,0.6,0.6,0.6,0.2,0.5,0.7,1.0,0.2,0.2,0.1,0.6),
        PersonaEnumType.CONJUNCTIVITIS to listOf(0.7,0.6,0.6,0.6,0.2,0.5,0.7,1.0,0.2,0.2,0.1,0.6),
        PersonaEnumType.CARDIO to listOf(1.0,0.6,0.8,0.8,0.5,0.6,0.3,0.2,0.9,0.7,0.3,0.2),
        PersonaEnumType.MIGRAINE to listOf(0.8,0.6,0.8,0.7,0.2,0.4,0.5,0.4,0.7,0.4,0.7,0.5),
        PersonaEnumType.MARS to listOf(1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0)
    )

    // Thresholds for subscore
    private val TH = mapOf(
        "pm25" to Triple(15.0,55.0,1.4),
        "pm10" to Triple(45.0,150.0,1.3),
        "o3_8h" to Triple(50.0,80.0,1.5),
        "no2_1h" to Triple(40.0,90.0,1.4),
        "no2_24h" to Triple(13.0,60.0,1.4),
        "co_8h" to Triple(4.0,10.0,1.3),
        "so2" to Triple(20.0,75.0,1.5),
        "hcho" to Triple(0.03,0.1,1.5)
    )

    private fun calcSub(x: Double?, safe: Double, high: Double, gamma: Double): Double {
        if(x == null || high <= safe) return 0.0
        val z = ((x - safe) / (high - safe)).coerceIn(0.0, 1.0)
        return 100.0 * z.pow(gamma)
    }

    private fun pollenSub(level: String?): Double {
        val m = mapOf("low" to 0.0,"moderate" to 33.0,"medium" to 33.0,"high" to 67.0,"very high" to 100.0)
        return level?.lowercase()?.let { m[it] } ?: 0.0
    }

    // 모든 Persona 점수 계산
    fun calculateAllScores(input: GetPersonaScoreRequest): List<GetPersonaScoreResponse> {
        val fixedSubs = calcFixedSubs(input)
        return factorWeights.map { (persona, weights) ->

            val activityMult = activityMultDefault[persona] ?: 1.0
            val contextMult = contextMultDefault[persona] ?: 1.0

            val weighted = weights.zip(fixedSubs).map { (w,s) -> w*s }
            val worst = weighted.maxOrNull() ?: 0.0
            val mean = if(weights.sum() > 0) weighted.sum() / weights.sum() else 0.0
            val S = alpha * worst + (1-alpha) * mean
            val adjustedS = (S * activityMult * contextMult).coerceIn(0.0,100.0)

            var score = String.format("%.1f", adjustedS).toDouble()

            var level = when {
                adjustedS < 25 -> "Low"
                adjustedS < 50 -> "Moderate"
                adjustedS < 75 -> "High"
                else -> "Very High"
            }

            if (persona == PersonaEnumType.MARS) {
                score = 100.0
                level = "Very High"
            }

            GetPersonaScoreResponse(
                persona = persona,
                score = score,
                level = level
            )
        }
    }

    // 단일 Persona 점수 조회
    fun calculateSingleScore(request: GetPersonaScoreRequest): GetPersonaScoreResponse {
        // 페르소나가 화성의 데이터인 경우는 무조건 100으로 나올 수 있도록 한다.
        if (request.persona == PersonaEnumType.MARS) {
            return GetPersonaScoreResponse(
                    persona = request.persona,
                    score = 100.0,
                    level = "Very High"
                )
        }

        return calculateAllScores(input = request).first { it.persona == request.persona }
    }

    // FactorInput → fixedSubs (서브스코어)
    private fun calcFixedSubs(input: GetPersonaScoreRequest): List<Double> {
        val subs = mutableListOf<Double>()
        subs.add(calcSub(input.pm25_24h, 15.0, 55.0, 1.4))
        subs.add(calcSub(input.pm10_24h, 45.0, 150.0, 1.3))
        subs.add(calcSub(input.o3_8h, 50.0, 80.0, 1.5))
        subs.add(max(calcSub(input.no2_1h,40.0,90.0,1.4), calcSub(input.no2_24h,13.0,60.0,1.4)))
        subs.add(calcSub(input.co_8h,4.0,10.0,1.3))
        subs.add(calcSub(input.so2,20.0,75.0,1.5))
        subs.add(calcSub(input.hcho,0.03,0.1,1.5))
        subs.add(pollenSub(input.pollen_level))
        subs.add(if(input.heat) 100.0 else 0.0)
        subs.add(if(input.cold) 100.0 else 0.0)
        subs.add(if(input.dP) 100.0 else 0.0)
        subs.add(if(input.storm) 100.0 else 0.0)
        return subs
    }
}
