package com.gisa.gisaplanos.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Schedule {

	@Id
	private Long id;

	@NotNull
	private String resourceId;

	@NotNull
	private LocalDate date;

	@OneToMany(mappedBy = "schedule")
	private List<TimeSchedule> timesSchedule;
}
