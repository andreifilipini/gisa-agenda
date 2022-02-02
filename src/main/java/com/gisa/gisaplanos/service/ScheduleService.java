package com.gisa.gisaplanos.service;

import com.gisa.gisacore.exception.InfraException;
import com.gisa.gisacore.util.StringUtil;
import com.gisa.gisaplanos.model.Schedule;
import com.gisa.gisaplanos.model.TimeSchedule;
import com.gisa.gisaplanos.repository.ScheduleRepository;
import com.gisa.gisaplanos.repository.TimeScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public void schedule(String resourceId, String date, String time) {
		Schedule schedule = find(resourceId, date);
		TimeSchedule timeSchedule = findTimeSchedule(schedule, time);

		if (timeSchedule == null) {
			throw new InfraException("A agenda não foi encontrada.");
		}
		timeSchedule.consume();

		timeScheduleRepository.save(timeSchedule);
	}

	public void cancelSchedule(String resourceId, String date, String time) {
		Schedule schedule = find(resourceId, date);
		TimeSchedule timeSchedule = findTimeSchedule(schedule, time);

		if (timeSchedule == null) {
			throw new InfraException("A agenda não foi encontrada.");
		}

		timeSchedule.release();

		timeScheduleRepository.save(timeSchedule);
	}

	private Schedule find(String resourceId, String date) {
		return StringUtil.isNotBlank(resourceId) && StringUtil.isNotBlank(date)
				? repository.findByResourceIdAndDate(resourceId, date)
				: null;
	}

	private TimeSchedule findTimeSchedule(Schedule schedule, String time) {
		return schedule != null && time != null
				? timeScheduleRepository.findByScheduleAndTime(schedule, time)
				: null;
	}
}
