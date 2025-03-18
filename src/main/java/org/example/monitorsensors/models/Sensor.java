package org.example.monitorsensors.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.monitorsensors.dto.Range;
import org.example.monitorsensors.enums.SensorType;
import org.example.monitorsensors.enums.Unit;
import org.example.monitorsensors.validators.ValidRange;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sensors")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 15)
    private String model;

    @Embedded
    @Column(nullable = false)
    @ValidRange
    @NotNull
    private Range range;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private SensorType type;

    @Enumerated(EnumType.STRING)
    private Unit unit;

    @Column(length = 40)
    private String location;

    @Column(length = 200)
    private String description;
}
