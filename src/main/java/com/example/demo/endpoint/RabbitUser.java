package com.example.demo.endpoint;

import com.example.demo.endpoint.dto.AccountResponseDTO;
import com.example.demo.endpoint.dto.UserDto;
import com.example.demo.endpoint.dto.UserResponseDTO;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class RabbitUser {
    private UserService userService;
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        System.out.println(rabbitTemplate);
        rabbitTemplate.setMessageConverter(conversor());
        return rabbitTemplate;
    }
    @Bean
    public Jackson2JsonMessageConverter conversor(){
        return new Jackson2JsonMessageConverter();
    }

    @RabbitListener(queues = {"accountUserRequestsQueueCreateUser"})
    public void createUser(UserDto userDto)
    {
        System.out.println(userService.createUser(userDto).getName());
        //userService.createUser(userDto);
    }

    @RabbitListener(queues = {"accountUserRequestsQueueGetAllAccounts"})
    public List<AccountResponseDTO> getAllAccounts(int idDocument)
    {
        return userService.consultAccounts(idDocument);
    }
    @RabbitListener(queues = {"accountUserRequestsQueueGetAllUsers"})
    public List<UserResponseDTO> getAllUsers()
    {
        return userService.getUsers();
    }

}
