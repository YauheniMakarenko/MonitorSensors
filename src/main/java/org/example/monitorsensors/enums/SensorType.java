package org.example.monitorsensors.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum SensorType {
    PRESSURE, TEMPERATURE, HUMIDITY, VOLTAGE;

    @JsonCreator
    public static SensorType fromString(String value) {
        return Enum.valueOf(SensorType.class, value.toUpperCase());
    }
}
