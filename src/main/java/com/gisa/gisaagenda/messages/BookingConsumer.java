package com.gisa.gisaagenda.messages;

import com.gisa.gisaagenda.service.ScheduleService;
import com.gisa.gisacore.dto.BasicTransactionResponseDTO;
import com.gisa.gisacore.exception.InfraException;
import com.gisa.gisacore.messages.AbstractRabbitConsumer;
import com.gisa.gisaagenda.dto.SchedulingRequestDTO;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Slf4j
@Component
public class BookingConsumer extends AbstractRabbitConsumer {

    @Inject
    private RabbitTemplate rabbitTemplate;

    @Inject
    private ScheduleService scheduleService;

    @Value("${queue.bookingScheduleResult}")
    private String bookingScheduleResultQueueName;

    @RabbitListener(queues = {"${queue.bookingSchedule}"})
    protected void receive(@Payload String body) {
        executeLoggin(body);
    }

    @Override
    protected void execute(@Payload String body) {
        Gson gson = new Gson();
        SchedulingRequestDTO request = gson.fromJson(body, SchedulingRequestDTO.class);
        BasicTransactionResponseDTO response;
        try {
            scheduleService.schedule(request.getResourceId(),
                    request.getDate(),
                    request.getTime());

             response = new BasicTransactionResponseDTO(request.getTransactionId(), true);
        } catch (InfraException ie) {
            response = new BasicTransactionResponseDTO(request.getTransactionId(), false);
        }
        rabbitTemplate.convertAndSend(this.bookingScheduleResultQueueName, gson.toJson(response));
    }

    @Override
    protected Logger getLogger() {
        return log;
    }
}
