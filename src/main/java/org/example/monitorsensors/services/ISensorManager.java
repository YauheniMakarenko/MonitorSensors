package org.example.monitorsensors.services;

import org.example.monitorsensors.dto.SensorDto;

import java.util.List;

public interface ISensorManager {

    Long saveSensor(SensorDto sensor);

    void removeSensor(Long id);

    List<SensorDto> searchSensors(String query);

    String updateSensor(Long id, SensorDto updatedSensor) throws Exception;

    SensorDto getSensorById(Long id) throws Exception;

    List<SensorDto> getAllSensors();
}
