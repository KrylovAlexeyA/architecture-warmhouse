package com.example.smart_home2.model

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "sensor")
data class Sensor(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val type: String,

    val location: String,
    val unit: String,
    val value: BigDecimal? = null,
    val status: String = "inactive"
)
