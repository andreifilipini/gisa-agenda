package com.gisa.gisaplanos.messages;

import com.gisa.gisacore.messages.AbstractRabbitConsumer;
import com.gisa.gisaplanos.dto.SchedulingRequestDTO;
import com.gisa.gisaplanos.service.ScheduleService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Slf4j
@Component
public class CancelScheduleConsumer extends AbstractRabbitConsumer {

    @Inject
    private RabbitTemplate rabbitTemplate;

    @Inject
    private ScheduleService scheduleService;

    @RabbitListener(queues = {"${queue.cancelSchedule}"})
    protected void receive(@Payload String body) {
        executeLoggin(body);
    }

    @Override
    protected void execute(@Payload String body) {
        Gson gson = new Gson();
        SchedulingRequestDTO request = gson.fromJson(body, SchedulingRequestDTO.class);
        scheduleService.cancelSchedule(request.getResourceId(),
                request.getDate(),
                request.getTime());
    }

    @Override
    protected Logger getLogger() {
        return log;
    }
}
