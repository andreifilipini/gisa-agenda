package com.gisa.gisaagenda.repository;

import com.gisa.gisaagenda.model.Schedule;
import com.gisa.gisaagenda.model.TimeSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeScheduleRepository extends JpaRepository<TimeSchedule, Long> {

    TimeSchedule findByScheduleAndTime(Schedule schedule, String time);
}
