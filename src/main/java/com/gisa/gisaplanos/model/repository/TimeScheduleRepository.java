package com.gisa.gisaplanos.model.repository;

import com.gisa.gisaplanos.model.Schedule;
import com.gisa.gisaplanos.model.TimeSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;

public interface TimeScheduleRepository extends JpaRepository<TimeSchedule, Long> {

    TimeSchedule findByScheduleAndTime(Schedule schedule, LocalTime localTime);
}
