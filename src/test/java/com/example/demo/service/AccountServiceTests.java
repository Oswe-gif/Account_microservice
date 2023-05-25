package com.example.demo.service;

import com.example.demo.endpoint.dto.AccountDto;
import com.example.demo.endpoint.dto.AccountResponseDTO;
import com.example.demo.entity.AccountEntity;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTests {
    @InjectMocks
    AccountService accountService;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private UserRepository userRepository;
    @Test
    void Given_AUserWithMoreThanFourRegisteredAccounts_When_Invoke_insertAccount_Then_throwRuntimeException() {
        ArrayList<AccountEntity> mock = new ArrayList<>();
        mock.add(new AccountEntity(  1,"Ahorro",0,"2025-03-24",1));
        mock.add(new AccountEntity(  2,"Ahorro",0,"2025-03-24",1));
        mock.add(new AccountEntity(  3,"Ahorro",0,"2025-03-24",1));
        mock.add(new AccountEntity(  4,"Ahorro",0,"2025-03-24",1));
        AccountDto accountDto = new AccountDto(5,"Ahorro",0,"2025-03-24",1);
        Mockito.when(accountRepository.getAllAccounts(accountDto.getUser())).thenReturn(mock);
        Assertions.assertThrows(RuntimeException.class, () -> {
            accountService.insertAccount(accountDto);
        });
        Mockito.verify(accountRepository).getAllAccounts(accountDto.getUser());
    }
    @Test
    void Given_AUserNOExist_When_Invoke_insertAccount_Then_throwRunTimeException() {
        ArrayList<AccountEntity> mock = new ArrayList<>();
        mock.add(new AccountEntity(  1,"Ahorro",0,"2025-03-24",1));
        mock.add(new AccountEntity(  2,"Ahorro",0,"2025-03-24",1));
        AccountDto accountDto = new AccountDto(3,"Ahorro",0,"2025-03-24",1);
        Mockito.when(userRepository.existsById(accountDto.getUser())).thenReturn(false);
        Assertions.assertThrows(RuntimeException.class, () -> {
            accountService.insertAccount(accountDto);
        });
        Mockito.verify(userRepository).existsById(accountDto.getUser());

    }
    @Test
    void Given_AnExistingUserWithLessThanFourAccounts_When_Invoke_insertAccount_Then_Return_AccountEntity() {
        ArrayList<AccountEntity> mock = new ArrayList<>();
        mock.add(new AccountEntity(  1,"Ahorro",0,"2025-03-24",1));
        mock.add(new AccountEntity(  2,"Ahorro",0,"2025-03-24",1));
        AccountDto accountDto = new AccountDto(3,"Ahorro",0,"2025-03-24",1);
        Mockito.when(userRepository.existsById(accountDto.getUser())).thenReturn(true);
        Mockito.when(accountRepository.getAllAccounts(accountDto.getUser())).thenReturn(mock);
        Mockito.when(accountRepository.save(new AccountEntity(3,"Ahorro",0,"2025-03-24",1))).thenReturn(new AccountEntity(3,"Ahorro",0,"2025-03-24",1));
        AccountResponseDTO account = accountService.insertAccount(accountDto);
        Assertions.assertEquals(new AccountResponseDTO(3,"Ahorro",0,"2025-03-24",1), account);
        Mockito.verify(userRepository).existsById(accountDto.getUser());
        Mockito.verify(accountRepository).getAllAccounts(accountDto.getUser());
        Mockito.verify(accountRepository).save(new AccountEntity(3,"Ahorro",0,"2025-03-24",1));

    }


    @Test
    void Given_AAccountNOExist_When_Invoke_checkBalance_Then_RuntimeException()
    {
        AccountDto accountDto = new AccountDto(1,"Ahorro",0,"2025-03-24",1);
        Mockito.when(accountRepository.existsById(accountDto.getId())).thenReturn(false);
        Assertions.assertThrows(RuntimeException.class, () -> {
            accountService.checkBalance(accountDto.getId());
        });
        Mockito.verify(accountRepository).existsById(accountDto.getId());

    }
    @Test
    void Given_AnExistingAccount_When_Invoke_checkBalance_Then_RuntimeException()
    {
        AccountDto accountDto = new AccountDto(1,"Ahorro",0,"2025-03-24",1);
        Mockito.when(accountRepository.existsById(accountDto.getId())).thenReturn(true);
        Optional<AccountEntity> accountEntity = Optional.of(new AccountEntity( 1,"Ahorro",0,"2025-03-24",1));
        Mockito.when(accountRepository.findById(accountDto.getId())).thenReturn(accountEntity);
        Assertions.assertEquals(new AccountResponseDTO(1,"Ahorro",0,"2025-03-24",1),accountService.checkBalance(accountDto.getId()));
        Mockito.verify(accountRepository).existsById(accountDto.getId());
        Mockito.verify(accountRepository).findById(accountDto.getId());

    }

}
