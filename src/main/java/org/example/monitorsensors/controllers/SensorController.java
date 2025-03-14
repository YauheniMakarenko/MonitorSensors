package org.example.monitorsensors.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.monitorsensors.models.Sensor;
import org.example.monitorsensors.services.SensorService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensors")
@RequiredArgsConstructor
public class SensorController {

    private final SensorService sensorService;

    @PreAuthorize("hasAnyRole('VIEWER', 'ADMIN')")
    @GetMapping
    @Operation(summary = "Get all sensors", description = "Allow to get all sensors")
    public List<Sensor> getAllSensors() {
        return sensorService.getAllSensors();
    }

    @PreAuthorize("hasAnyRole('VIEWER', 'ADMIN')")
    @GetMapping("/{id}")
    @Operation(summary = "Get sensor by id", description = "Allow to get sensor by id")
    public Sensor getSensorById(@PathVariable Long id) {
        return sensorService.getSensorById(id);
    }

    @PreAuthorize("hasAnyRole('VIEWER', 'ADMIN')")
    @GetMapping("/search")
    @Operation(summary = "Search sensors", description = "Allow to search sensors by model or name")
    public List<Sensor> searchSensors(@RequestParam String query) {
        return sensorService.searchSensors(query);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Operation(summary = "Create sensor", description = "Allow to add sensor in DB")
    public Long createSensor(@RequestBody @Valid Sensor sensor) {
        return sensorService.saveSensor(sensor).getId();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Update sensor", description = "Allow to update sensor in DB")
    public Long updateSensor(@PathVariable Long id, @RequestBody @Valid Sensor sensor) {
        return sensorService.updateSensor(id, sensor).getId();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Remove sensor", description = "Allow to remove sensor from DB")
    public String deleteSensor(@PathVariable Long id) {
        sensorService.removeSensor(id);
        return String.format("Sensor %s was removed", id);
    }
}
