package com.gisa.gisaagenda.controller;

import com.gisa.gisaagenda.dto.TimeScheduleDTO;
import com.gisa.gisaagenda.service.ScheduleService;
import com.gisa.gisacore.exception.InfraException;
import com.gisa.gisaagenda.dto.ScheduleDTO;
import com.gisa.gisaagenda.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/agenda")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<List<ScheduleDTO>> findAll(@RequestParam("resourceId") String resourceId) {
        try {
            return ResponseEntity.ok(
                    scheduleService.findTimeSchedule(resourceId).stream()
                            .map(this::toDTO)
                            .collect(Collectors.toList()));
        } catch (InfraException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    private ScheduleDTO toDTO(Schedule schedule) {
        return ScheduleDTO.builder()
                .date(schedule.getDate())
                .timesSchedule(
                        schedule.getTimesSchedule().stream().map(
                                timeSchedule -> TimeScheduleDTO.builder()
                                        .time(timeSchedule.getTime())
                                        .available(timeSchedule.isAvailable())
                                        .build())
                                .collect(Collectors.toList()))
                .build();
    }

}
