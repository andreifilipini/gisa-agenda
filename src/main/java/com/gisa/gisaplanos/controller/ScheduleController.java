package com.gisa.gisaplanos.controller;

import com.gisa.gisacore.exception.InfraException;
import com.gisa.gisaplanos.dto.ScheduleDTO;
import com.gisa.gisaplanos.dto.TimeScheduleDTO;
import com.gisa.gisaplanos.model.Schedule;
import com.gisa.gisaplanos.model.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.stream.Collectors;

import static com.gisa.gisacore.util.DateUtil.simpleDate;
import static com.gisa.gisacore.util.DateUtil.simpleTime;

@RestController
@RequestMapping("/agenda")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<List<ScheduleDTO>> findAll(@QueryParam("resourceId") String resourceId) {
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
                .date(schedule.getDate().format(simpleDate))
                .timesSchedule(
                        schedule.getTimesSchedule().stream().map(
                                timeSchedule -> TimeScheduleDTO.builder()
                                        .time(timeSchedule.getTime().format(simpleTime))
                                        .available(timeSchedule.isAvailable())
                                        .build())
                                .collect(Collectors.toList()))
                .build();
    }

}
