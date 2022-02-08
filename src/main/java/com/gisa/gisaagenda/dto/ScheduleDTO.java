package com.gisa.gisaagenda.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ScheduleDTO {

	private String date;

	private List<TimeScheduleDTO> timesSchedule;
}
