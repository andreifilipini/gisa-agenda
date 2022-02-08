package com.gisa.gisaagenda.repository;

import com.gisa.gisaagenda.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByResourceId(String resourceId);

    Schedule findByResourceIdAndDate(String resourceId, String date);
}
