package com.example.demo.endpoint;

import com.example.demo.endpoint.dto.AccountDto;
import com.example.demo.endpoint.dto.AccountResponseDTO;
import com.example.demo.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;



@Component
@NoArgsConstructor
@AllArgsConstructor
public class RabbitAccount {
    private AccountService accountService;
    /*@Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(conversor());
        return rabbitTemplate;
    }
    @Bean
    public Jackson2JsonMessageConverter conversor(){
        return new Jackson2JsonMessageConverter();
    }


    @RabbitListener(queues = {"accountUserRequestsQueueInsertAccount"})
    public AccountResponseDTO insertAccount(AccountDto accountDto)
    {
        return accountService.insertAccount(accountDto);
    }

    @RabbitListener(queues = {"accountUserRequestsQueueCheckBalance"})
    public AccountResponseDTO checkBalance(int idAccount)
    {
        return accountService.checkBalance(idAccount);
    }*/

}
