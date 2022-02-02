package com.gisa.gisaplanos.repository;

import com.gisa.gisaplanos.model.Schedule;
import com.gisa.gisaplanos.model.TimeSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeScheduleRepository extends JpaRepository<TimeSchedule, Long> {

    TimeSchedule findByScheduleAndTime(Schedule schedule, String time);
}
