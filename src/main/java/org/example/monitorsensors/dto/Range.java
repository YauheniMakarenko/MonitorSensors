package org.example.monitorsensors.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.monitorsensors.validators.ValidRange;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ValidRange
public class Range {

    @Column(name = "rangeFrom")
    @Min(value = 0, message = "Range 'from' must be positive")
    private int from;

    @Column(nullable = false, name = "rangeTo")
    @Min(value = 0, message = "Range 'to' must be positive")
    @NotNull
    private int to;
}
