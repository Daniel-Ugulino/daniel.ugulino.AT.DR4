package com.example.itemService.Consumer;

import com.example.itemService.Dto.RentDto;
import com.example.itemService.Service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemConsumer {
    @Autowired
    private ItemService itemService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "item-queue")
    public void receiveMessage(String message) throws Exception {
        System.out.println(message);
        RentDto rentDto = objectMapper.readValue(message, RentDto.class);
        itemService.updateAmount(rentDto);
    }
}
