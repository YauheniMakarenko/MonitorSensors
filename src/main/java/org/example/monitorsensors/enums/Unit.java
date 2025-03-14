package org.example.monitorsensors.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Unit {
    BAR, VOLTAGE, CELSIUS, PERCENT;

    @JsonCreator
    public static Unit fromString(String value) {
        return Enum.valueOf(Unit.class, value.toUpperCase());
    }
}
