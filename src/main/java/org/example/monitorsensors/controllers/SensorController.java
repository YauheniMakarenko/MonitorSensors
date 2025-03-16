package org.example.monitorsensors.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.monitorsensors.dto.ErrorResponse;
import org.example.monitorsensors.dto.SensorDto;
import org.example.monitorsensors.services.SensorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sensors")
@RequiredArgsConstructor
public class SensorController {

    private final SensorService sensorService;

    @PreAuthorize("hasAnyRole('VIEWER', 'ADMIN')")
    @GetMapping
    @Operation(summary = "Get all sensors", description = "Allow to get all sensors")
    public List<SensorDto> getAllSensors() {
        return sensorService.getAllSensors();
    }

    @PreAuthorize("hasAnyRole('VIEWER', 'ADMIN')")
    @GetMapping("/{id}")
    @Operation(summary = "Get sensor by id", description = "Allow to get sensor by id")
    public SensorDto getSensorById(@PathVariable Long id) {
        return sensorService.getSensorById(id);
    }

    @PreAuthorize("hasAnyRole('VIEWER', 'ADMIN')")
    @GetMapping("/search")
    @Operation(summary = "Search sensors", description = "Allow to search sensors by model or name")
    public List<SensorDto> searchSensors(@RequestParam String query) {
        return sensorService.searchSensors(query);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Operation(summary = "Create sensor", description = "Allow to add sensor in DB")
    public Long createSensor(@Valid @RequestBody SensorDto dto) {
        return sensorService.saveSensor(dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Update sensor", description = "Allow to update sensor in DB")
    public String updateSensor(@PathVariable Long id, @Valid @RequestBody SensorDto dto) {
        return sensorService.updateSensor(id, dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Remove sensor", description = "Allow to remove sensor from DB")
    public String deleteSensor(@PathVariable Long id) {
        sensorService.removeSensor(id);
        return String.format("Sensor %s was removed", id);
    }

    // Added for myself
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
