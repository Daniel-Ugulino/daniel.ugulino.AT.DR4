package com.example.rentService.Config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQ {
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("rent-exchange");
    }

    @Bean
    public Queue itemQueue() {
        return new Queue("item-queue", true);
    }

    @Bean
    public Binding itemBinding() {
        return BindingBuilder.bind(itemQueue()).to(exchange()).with("item-routing-key");
    }

    @Bean
    public Queue companyQueue() {
        return new Queue("company-queue", true);
    }

    @Bean
    public Binding rentBinding() {
        return BindingBuilder.bind(companyQueue()).to(exchange()).with("company-routing-key");
    }


}
