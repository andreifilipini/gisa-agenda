package com.gisa.gisaplanos.model.repository;

import com.gisa.gisaplanos.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByResourceId(String resourceId);

    Schedule findByResourceIdAndDate(String resourceId, String date);
}
