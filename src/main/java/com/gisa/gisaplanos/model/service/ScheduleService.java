package com.gisa.gisaplanos.model.service;

import com.gisa.gisacore.util.StringUtil;
import com.gisa.gisaplanos.model.Schedule;
import com.gisa.gisaplanos.model.TimeSchedule;
import com.gisa.gisaplanos.model.repository.ScheduleRepository;
import com.gisa.gisaplanos.model.repository.TimeScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

@Service
public class ScheduleService {

	@Autowired
	private ScheduleRepository repository;

	@Autowired
	private TimeScheduleRepository timeScheduleRepository;

	public List<Schedule> findTimeSchedule(String resourceId) {
		return StringUtil.isBlank(resourceId)
				? Collections.emptyList()
				: repository.findByResourceId(resourceId);
	}

	public void schedule(String resourceId, LocalDate localDate, LocalTime localTime) {
		Schedule schedule = find(resourceId, localDate);
		TimeSchedule timeSchedule = findTimeSchedule(schedule, localTime);

		timeSchedule.consume();

		timeScheduleRepository.save(timeSchedule);
	}

	public void cancelSchedule(String resourceId, LocalDate localDate, LocalTime localTime) {
		Schedule schedule = find(resourceId, localDate);
		TimeSchedule timeSchedule = findTimeSchedule(schedule, localTime);

		timeSchedule.release();

		timeScheduleRepository.save(timeSchedule);
	}

	private Schedule find(String resourceId, LocalDate localDate) {
		return StringUtil.isBlank(resourceId) && localDate != null
				? repository.findByResourceIdAndDate(resourceId, localDate)
				: null;
	}

	private TimeSchedule findTimeSchedule(Schedule schedule, LocalTime localTime) {
		return schedule != null && localTime != null
				? timeScheduleRepository.findByScheduleAndTime(schedule, localTime)
				: null;
	}
}
