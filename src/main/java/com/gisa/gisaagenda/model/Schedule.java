package com.gisa.gisaagenda.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String resourceId;

	@NotNull
	private String date;

	@OneToMany(mappedBy = "schedule")
	private List<TimeSchedule> timesSchedule;
}
