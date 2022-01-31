package com.gisa.gisaplanos.model;

import com.gisa.gisacore.exception.InfraException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor
public class TimeSchedule {

	@Id
	private Long id;

	@NotNull
	@ManyToOne
	private Schedule schedule;

	@NotNull
	private LocalTime time;

	private boolean available;

	public void consume() {
		if(this.available) {
			throw new InfraException("A agenda está indisponível.");
		}
		this.available = false;
	}

	public void release() {
		this.available = true;
	}
}
