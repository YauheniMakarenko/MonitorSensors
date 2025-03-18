package org.example.monitorsensors.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.monitorsensors.validators.ValidRange;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SensorDto {

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters")
    private String name;

    @NotBlank(message = "Model cannot be empty")
    @Size(max = 15, message = "Model cannot exceed 15 characters")
    private String model;

    @NotNull(message = "Range cannot be null")
    @ValidRange
    private Range range;

    @NotBlank(message = "Type cannot be empty")
    private String type;

    private String unit;

    @Size(max = 40, message = "Location cannot exceed 40 characters")
    private String location;

    @Size(max = 200, message = "Description cannot exceed 200 characters")
    private String description;
}
