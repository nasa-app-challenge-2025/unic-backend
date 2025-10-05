package com.weathering.backend.domain.enum

enum class PersonaEnumType(
    val description: String,
) {
    ASTHMA("천식"),
    COPD("만성폐쇄성폐질환"),
    RHINITIS("비염"),
    CONJUNCTIVITIS("결막염"),
    CARDIO("심혈관 질환"),
    MIGRAINE("편두통"),
    MARS("화성 데이터"),
    ;
}
