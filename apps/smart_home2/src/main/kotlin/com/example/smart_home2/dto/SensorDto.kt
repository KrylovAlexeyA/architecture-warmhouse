package com.example.smart_home2.dto

import java.math.BigDecimal

data class CreateSensorRequest(
    val name: String,
    val type: String,
    val location: String,
    val unit: String
)

data class UpdateSensorRequest(
    val name: String,
    val type: String,
    val location: String,
    val unit: String
)

data class UpdateSensorValueRequest(
    val value: BigDecimal,
    val status: String
)