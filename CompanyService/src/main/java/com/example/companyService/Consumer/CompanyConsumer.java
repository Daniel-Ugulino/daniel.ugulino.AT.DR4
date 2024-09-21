package com.example.companyService.Consumer;
import com.example.companyService.Dto.RentDto;
import com.example.companyService.Service.CompanyService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CompanyConsumer {
    @Autowired
    private CompanyService companyService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "company-queue")
    public void receiveMessage(String message) throws Exception {
        System.out.println(message);
        RentDto rentDto = objectMapper.readValue(message, RentDto.class);
        companyService.addOrder(rentDto);
    }
}
