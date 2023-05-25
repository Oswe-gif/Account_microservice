package com.example.demo.endpoint;

import com.example.demo.endpoint.dto.AccountDto;
import com.example.demo.endpoint.dto.UserDto;
import com.example.demo.service.AccountService;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

@Component
@AllArgsConstructor
public class UserAccountListener {
    private AccountService accountService;
    private UserService userService;

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(conversor());
        return rabbitTemplate;
    }
    @Bean
    public Jackson2JsonMessageConverter conversor(){
        return new Jackson2JsonMessageConverter();
    }

    @RabbitListener(queues = {"accounts"})
    public void createAccount(AccountDto accountDto) {
        accountService.insertAccount(accountDto);
    }

    @RabbitListener(queues = {"users"})
    public void createUser(UserDto userDto) {
        userService.createUser(userDto);
    }
}

