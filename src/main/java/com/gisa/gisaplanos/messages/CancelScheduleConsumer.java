package com.gisa.gisaplanos.messages;

import com.gisa.gisacore.exception.InfraException;
import com.gisa.gisacore.util.StringUtil;
import com.gisa.gisaplanos.dto.SchedulingRequestDTO;
import com.gisa.gisaplanos.service.ScheduleService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.inject.Inject;


@Slf4j
@Component
public class CancelScheduleConsumer {

    @Inject
    private RabbitTemplate rabbitTemplate;

    @Inject
    private ScheduleService scheduleService;

    @RabbitListener(queues = {"${queue.cancelSchedule}"})
    private void receive(@Payload String body) {
        Gson gson = new Gson();
        try {
            SchedulingRequestDTO request = gson.fromJson(body, SchedulingRequestDTO.class);
            scheduleService.cancelSchedule(request.getResourceId(),
                    request.getDate(),
                    request.getTime());
        } catch (InfraException ie) {
            logWarn(ie.getMessage(), body);
        } catch (Exception e) {
            logError(e.getMessage(), body);
        }
    }

    private void logWarn(String message, String body) {
        log.warn(formatErrorMessage(message, body));
    }

    private void logError(String message, String body) {
        log.error(formatErrorMessage(message, body));
    }

    private String formatErrorMessage(String message, String body) {
        return String.format("message: %s; body: %s", message, StringUtil.isBlank(body) ? "null" : body);
    }
}
