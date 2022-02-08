package com.gisa.gisaagenda.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
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
	private String date;

	@OneToMany(mappedBy = "schedule")
	private List<TimeSchedule> timesSchedule;
}
