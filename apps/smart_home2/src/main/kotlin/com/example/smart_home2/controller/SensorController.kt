package com.example.smart_home2.controller

import com.example.smart_home2.dto.CreateSensorRequest
import com.example.smart_home2.dto.UpdateSensorValueRequest
import com.example.smart_home2.model.Sensor
import com.example.smart_home2.repository.SensorRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.RoundingMode

@RestController
@RequestMapping("/api/v1/sensors")
class SensorController(
    private val repo: SensorRepository
) {

    @GetMapping
    fun getAllSensors(): List<Sensor> = repo.findAll()

    @PostMapping
    fun createSensor(@RequestBody req: CreateSensorRequest): Sensor {
        return repo.save(
            Sensor(
                name = req.name,
                type = req.type,
                location = req.location,
                unit = req.unit,
            )
        )
    }

    @GetMapping("/{id}")
    fun getSensor(@PathVariable id: Long): Sensor {
        return repo.findById(id).orElseThrow { RuntimeException("Sensor not found") }
    }

    @PutMapping("/{id}")
    fun updateSensor(@PathVariable id: Long, @RequestBody req: CreateSensorRequest): Sensor {
        val existing = repo.findById(id).orElseThrow { RuntimeException("Sensor not found") }
        return repo.save(
            existing.copy(
                name = req.name,
                type = req.type,
                location = req.location,
                unit = req.unit,
            )
        )
    }

    @DeleteMapping("/{id}")
    fun deleteSensor(@PathVariable id: Long): ResponseEntity<Void> {
        if (!repo.existsById(id)) throw RuntimeException("Sensor not found")
        repo.deleteById(id)
        return ResponseEntity.noContent().build()
    }

    @PatchMapping("/{id}/value")
    fun updateValue(@PathVariable id: Long, @RequestBody req: UpdateSensorValueRequest): Sensor {
        val sensor = repo.findById(id).orElseThrow { RuntimeException("Sensor not found") }
        return repo.save(
            sensor.copy(
                value = req.value.setScale(1, RoundingMode.HALF_EVEN),
                status = req.status
            )
        )
    }
}

@RestController
class HealthController {
    @GetMapping("/health")
    fun health() = mapOf("status" to "ok")
}