package org.example.monitorsensors.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.monitorsensors.models.Sensor;
import org.example.monitorsensors.repository.SensorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SensorService implements ISensorManager {

    private final SensorRepository sensorRepository;

    public Sensor saveSensor(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    public void removeSensor(Long id) {
        sensorRepository.deleteById(id);
    }

    public List<Sensor> searchSensors(String query) {
        return sensorRepository.findByNameContainingIgnoreCaseOrModelContainingIgnoreCase(query, query);
    }

    public Sensor updateSensor(Long id, Sensor updatedSensor) {
        Sensor existingSensor = getSensorById(id);
        existingSensor.setName(updatedSensor.getName());
        existingSensor.setModel(updatedSensor.getModel());
        existingSensor.setType(updatedSensor.getType());
        existingSensor.setRange(updatedSensor.getRange());
        existingSensor.setUnit(updatedSensor.getUnit());
        existingSensor.setLocation(updatedSensor.getLocation());
        existingSensor.setDescription(updatedSensor.getDescription());
        return sensorRepository.save(existingSensor);
    }

    public Sensor getSensorById(Long id) {
        return sensorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Sensor not found with id: " + id));
    }

    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }
}
