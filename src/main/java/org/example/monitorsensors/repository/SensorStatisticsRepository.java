package org.example.monitorsensors.repository;

import org.example.monitorsensors.models.SensorStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SensorStatisticsRepository extends JpaRepository<SensorStatistics, Long> {

    List<SensorStatistics> findByStatisticsDateBetween(LocalDate startDate, LocalDate endDate);
}
