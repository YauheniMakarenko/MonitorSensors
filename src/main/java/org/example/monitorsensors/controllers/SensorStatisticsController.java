package org.example.monitorsensors.controllers;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.example.monitorsensors.models.SensorStatistics;
import org.example.monitorsensors.services.SensorStatisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
public class SensorStatisticsController {

    private final SensorStatisticsService statisticsService;

    @GetMapping
    public List<SensorStatistics> getStatistics(
            @RequestParam @Parameter(name = "startDate", description = "Start Date", example = "2025-01-01") LocalDate startDate,
            @RequestParam @Parameter(name = "endDate", description = "End Date", example = "2025-03-18") LocalDate endDate) {
        return statisticsService.getStatisticsBetweenDates(startDate, endDate);
    }
}
