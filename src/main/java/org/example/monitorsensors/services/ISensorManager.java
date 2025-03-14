package org.example.monitorsensors.services;

import org.example.monitorsensors.models.Sensor;

import java.util.List;

public interface ISensorManager {

    Sensor saveSensor(Sensor sensor);

    void removeSensor(Long id);

    List<Sensor> searchSensors(String query);

    Sensor updateSensor(Long id, Sensor updatedSensor) throws Exception;

    Sensor getSensorById(Long id) throws Exception;

    List<Sensor> getAllSensors();
}
