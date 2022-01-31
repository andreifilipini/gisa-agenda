package com.gisa.gisaplanos.messages;

import com.gisa.gisacore.dto.BasicTransactionResponseDTO;
import com.gisa.gisacore.exception.InfraException;
import com.gisa.gisaplanos.dto.SchedulingRequestDTO;
import com.gisa.gisaplanos.model.service.ScheduleService;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class CancelScheduleConsumer {

    @Inject
    private RabbitTemplate rabbitTemplate;

    @Inject
    private ScheduleService scheduleService;

    @Value("${queue.cancelScheduleResult}")
    private String cancelScheduleResultQueueName;

    @RabbitListener(queues = {"${queue.cancelSchedule}"})
    private void receive(@Payload String body) {
        Gson gson = new Gson();
        SchedulingRequestDTO request = gson.fromJson(body, SchedulingRequestDTO.class);
        BasicTransactionResponseDTO response;
        try {
        scheduleService.cancelSchedule(request.getResourceId(),
                request.getDate(),
                request.getTime());
            response = new BasicTransactionResponseDTO(request.getTransactionId(), true);
        } catch (InfraException ie) {
            response = new BasicTransactionResponseDTO(request.getTransactionId(), false);
        }
        rabbitTemplate.convertAndSend(this.cancelScheduleResultQueueName, gson.toJson(response));
    }
}
