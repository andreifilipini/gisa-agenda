package com.gisa.gisaplanos.messages;

import com.gisa.gisacore.util.DateUtil;
import com.gisa.gisaplanos.dto.SchedulingRequestDTO;
import com.gisa.gisacore.dto.BasicTransactionResponseDTO;
import com.gisa.gisaplanos.model.service.ScheduleService;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class BookingConsumer {

    @Inject
    private RabbitTemplate rabbitTemplate;

    @Inject
    private ScheduleService scheduleService;

    @Value("${queue.bookingScheduleResult}")
    private String bookingScheduleResultQueueName;

    @RabbitListener(queues = {"${queue.bookingSchedule}"})
    private void receive(@Payload String body) {
        Gson gson = new Gson();
        SchedulingRequestDTO request = gson.fromJson(body, SchedulingRequestDTO.class);

        scheduleService.schedule(request.getResourceId(),
                DateUtil.toSimplelLocalDate(request.getDate()),
                DateUtil.toSimplelLocalTime(request.getTime()));

        BasicTransactionResponseDTO response = new BasicTransactionResponseDTO(request.getTransactionId(), true);
        rabbitTemplate.convertAndSend(this.bookingScheduleResultQueueName, gson.toJson(response));
    }
}
