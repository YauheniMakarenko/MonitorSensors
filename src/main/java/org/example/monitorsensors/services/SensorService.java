package org.example.monitorsensors.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.monitorsensors.dto.SensorDto;
import org.example.monitorsensors.mapper.SensorMapper;
import org.example.monitorsensors.models.Sensor;
import org.example.monitorsensors.repository.SensorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SensorService implements ISensorManager {

    private final SensorRepository sensorRepository;
    private final SensorMapper sensorMapper;

    public Long saveSensor(SensorDto dto) {
        Sensor sensor = sensorMapper.toEntity(dto);
        return sensorRepository.save(sensor).getId();
    }

    public void removeSensor(Long id) {
        sensorRepository.deleteById(id);
    }

    public List<SensorDto> searchSensors(String query) {
        return sensorRepository.findByNameContainingIgnoreCaseOrModelContainingIgnoreCase(query, query)
                .stream()
                .map(sensorMapper::toDto)
                .toList();
    }

    public String updateSensor(Long id, SensorDto dto) {
        Optional<Sensor> sensor = sensorRepository.findById(id);
        if (sensor.isPresent()) {
            sensorMapper.updateSensorFromDto(dto, sensor.get());
            sensorRepository.save(sensor.get());
            return String.format("Sensor with %s was updated successful", id);
        } else {
            throw new EntityNotFoundException("Sensor with id " + id + " not found");
        }
    }

    public SensorDto getSensorById(Long id) {
        return sensorRepository.findById(id).map(sensorMapper::toDto).orElseThrow(() -> new EntityNotFoundException("Sensor not found with id: " + id));
    }

    public List<SensorDto> getAllSensors() {
        return sensorRepository.findAll().stream()
                .map(sensorMapper::toDto)
                .toList();
    }
}
