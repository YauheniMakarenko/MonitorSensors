package org.example.monitorsensors.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "Name cannot be empty")
    @Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters")
    private String name;

    @Column(nullable = false, length = 15)
    @NotBlank(message = "Model cannot be empty")
    @Size(max = 15, message = "Model cannot exceed 15 characters")
    private String model;

    @Embedded
    @Column(nullable = false)
    @NotNull
    @ValidRange
    private Range range;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private SensorType type;

    @Enumerated(EnumType.STRING)
    private Unit unit;

    @Column(length = 40)
    @Size(max = 40, message = "Location cannot exceed 40 characters")
    private String location;

    @Column(length = 200)
    @Size(max = 200, message = "Description cannot exceed 200 characters")
    private String description;
}
