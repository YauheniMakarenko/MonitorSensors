package org.example.monitorsensors.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "sensor_statistics")
@Data
public class SensorStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "statistics_date", nullable = false)
    private LocalDate statisticsDate;

    @Column(name = "total_sensors", nullable = false)
    private int totalSensors;

    @Column(name = "temperature_sensors", nullable = false)
    private int temperatureSensors;

    @Column(name = "pressure_sensors", nullable = false)
    private int pressureSensors;

    @Column(name = "humidity_sensors", nullable = false)
    private int humiditySensors;
}
