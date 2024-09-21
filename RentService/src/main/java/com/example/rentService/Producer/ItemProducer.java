package com.example.rentService.Producer;

import com.example.rentService.Dto.RentProducerDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ItemProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(RentProducerDto rentProducerDto) throws JsonProcessingException {
        rabbitTemplate.convertAndSend(
            "rent-exchange",
            "item-routing-key",
            objectMapper.writeValueAsString(rentProducerDto)
    );
    }
}
