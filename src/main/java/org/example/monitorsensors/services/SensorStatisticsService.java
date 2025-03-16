package org.example.monitorsensors.services;

import lombok.RequiredArgsConstructor;
import org.example.monitorsensors.dto.SensorDto;
import org.example.monitorsensors.enums.SensorType;
import org.example.monitorsensors.mapper.SensorMapper;
import org.example.monitorsensors.models.Sensor;
import org.example.monitorsensors.models.SensorStatistics;
import org.example.monitorsensors.repository.SensorStatisticsRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SensorStatisticsService {

    private final SensorStatisticsRepository statisticsRepository;
    private final RestTemplate restTemplate;
    private final SensorMapper sensorMapper;

    @Value("${sensor.api.url}")
    private String sensorApiUrl;

    @Value("${sensor.api.admin.username}")
    private String username;

    @Value("${sensor.api.admin.password}")
    private String password;

    @Scheduled(cron = "0 0 2 * * ?")
    public void collectSensorStatistics() {
        System.out.println("Collecting sensor statistics");
        List<Sensor> sensors = fetchSensorsFromApi();
        if (sensors.isEmpty()) {
            return;
        }

        int totalSensors = sensors.size();
        int temperatureSensors = (int) sensors.stream().filter(s -> s.getType() == SensorType.TEMPERATURE).count();
        int pressureSensors = (int) sensors.stream().filter(s -> s.getType() == SensorType.PRESSURE).count();
        int humiditySensors = (int) sensors.stream().filter(s -> s.getType() == SensorType.HUMIDITY).count();

        SensorStatistics statistics = new SensorStatistics();
        statistics.setStatisticsDate(LocalDate.now());
        statistics.setTotalSensors(totalSensors);
        statistics.setTemperatureSensors(temperatureSensors);
        statistics.setPressureSensors(pressureSensors);
        statistics.setHumiditySensors(humiditySensors);

        statisticsRepository.save(statistics);
    }

    public List<SensorStatistics> getStatisticsBetweenDates(LocalDate startDate, LocalDate endDate) {
        return statisticsRepository.findByStatisticsDateBetween(startDate, endDate);
    }

    private List<Sensor> fetchSensorsFromApi() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", basicAuthHeader());
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<SensorDto[]> response = restTemplate.exchange(
                sensorApiUrl, HttpMethod.GET, entity, SensorDto[].class
        );
        return response.getBody() != null
                ? Arrays.stream(response.getBody()).map(sensorMapper::toEntity).toList()
                : List.of();
    }

    private String basicAuthHeader() {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encode(auth.getBytes(StandardCharsets.UTF_8));
        return "Basic " + new String(encodedAuth, StandardCharsets.UTF_8);
    }
}
