package org.example.monitorsensors.mapper;

import org.example.monitorsensors.dto.SensorDto;
import org.example.monitorsensors.enums.SensorType;
import org.example.monitorsensors.enums.Unit;
import org.example.monitorsensors.models.Sensor;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SensorMapper {

    @Mapping(target = "type", expression = "java(mapSensorTypeToString(sensor))")
    @Mapping(target = "unit", expression = "java(mapUnitToString(sensor))")
    SensorDto toDto(Sensor sensor);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "type", expression = "java(mapStringToSensorType(sensorDto))")
    @Mapping(target = "unit", expression = "java(mapStringToUnit(sensorDto))")
    Sensor toEntity(SensorDto sensorDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "type", expression = "java(mapStringToSensorType(sensorDto))")
    @Mapping(target = "unit", expression = "java(mapStringToUnit(sensorDto))")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSensorFromDto(SensorDto sensorDto, @MappingTarget Sensor sensor);

    @Named("mapStringToUnit")
    default Unit mapStringToUnit(SensorDto dto) {
        if (dto.getUnit() == null)
            return null;
        return switch (dto.getUnit()) {
            case "bar" -> Unit.BAR;
            case "voltage" -> Unit.VOLTAGE;
            case "°С" -> Unit.CELSIUS;
            case "%" -> Unit.PERCENTAGE;
            default -> throw new IllegalArgumentException("Invalid unit: " + dto.getUnit());
        };
    }

    @Named("mapUnitToString")
    default String mapUnitToString(Sensor entity) {
        if (entity.getUnit() == null)
            return null;
        return switch (entity.getUnit()) {
            case BAR -> "bar";
            case VOLTAGE -> "voltage";
            case CELSIUS -> "°C";
            case PERCENTAGE -> "%";
        };
    }

    @Named("mapStringToSensorType")
    default SensorType mapStringToSensorType(SensorDto dto) {
        if (dto.getType() == null)
            return null;
        return SensorType.valueOf(dto.getType().toUpperCase());
    }

    @Named("mapSensorTypeToString")
    default String mapSensorTypeToString(Sensor entity) {
        return (entity.getType() != null) ? entity.getType().name().toLowerCase() : null;
    }
}