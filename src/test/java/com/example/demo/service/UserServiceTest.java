package com.example.demo.service;

import com.example.demo.controller.dto.AccountResponseDTO;
import com.example.demo.controller.dto.UserDto;
import com.example.demo.controller.dto.UserResponseDTO;
import com.example.demo.entity.AccountEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.AccountRepositoryH2;
import com.example.demo.repository.UserRepositoryH2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    UserService userService;
    @Mock
    private UserRepositoryH2 userRepository;
    @Mock
    private AccountRepositoryH2 accountRepository;
    @Test
    void Given_UserDataInDto_When_Invoke_createUser_Then_Return_UserEntity() {
        UserDto userDto = new UserDto(5,"Pepito","Perez","2025-03-24");
        UserResponseDTO user = userService.createUser(userDto);
        Assertions.assertEquals(new UserResponseDTO(5,"Pepito","Perez","2025-03-24"), user);
        Mockito.verify(userRepository).save(new UserEntity(5,"Pepito","Perez","2025-03-24"));

    }
    @Test
    void Given_UserDocumentExists_When_Invoke_consultAccounts_Then_Return_AccountEntityList() {
        UserDto userDto = new UserDto(10,"Lola","Londoño","2025-03-24");
        ArrayList<AccountEntity> expectedAccounts = new ArrayList<>();
        ArrayList<AccountResponseDTO> expectedAccounts2 = new ArrayList<>();
        expectedAccounts.add(new AccountEntity(  1,"Ahorro",100,"2025-02-24",10));
        expectedAccounts.add(new AccountEntity(  2,"Corriente",200,"2025-03-24",10));
        expectedAccounts.add(new AccountEntity(  3,"Ahorro",300,"2025-04-24",10));
        expectedAccounts2.add(new AccountResponseDTO(  1,"Ahorro",100,"2025-02-24",10));
        expectedAccounts2.add(new AccountResponseDTO(  2,"Corriente",200,"2025-03-24",10));
        expectedAccounts2.add(new AccountResponseDTO(  3,"Ahorro",300,"2025-04-24",10));
        Mockito.when(userRepository.existsById(userDto.getDocument())).thenReturn(true);
        Mockito.when(accountRepository.getAllAccounts(userDto.getDocument())).thenReturn(expectedAccounts);
        List<AccountResponseDTO> accounts = userService.consultAccounts(userDto.getDocument());
        Assertions.assertEquals(expectedAccounts2, accounts);
        Mockito.verify(userRepository).existsById(userDto.getDocument());
        Mockito.verify(accountRepository).getAllAccounts(userDto.getDocument());

    }
    @Test
    void Given_UserDocumentDONTExists_When_Invoke_consultAccounts_Then_throwRuntimeException() {
        UserDto userDto = new UserDto(2,"Tomate","Torrez","2025-03-24");
        Mockito.when(userRepository.existsById(userDto.getDocument())).thenReturn(false);
        Assertions.assertThrows(RuntimeException.class, () ->{
            userService.consultAccounts(userDto.getDocument());
        });
        Mockito.verify(userRepository).existsById(userDto.getDocument());

    }
    @Test
    void Given_requestAllUsers_When_Invoke_getUsers_Then_Return_ListWithAllUsers(){
        List<UserEntity> expectedUsers = new ArrayList<>();
        List<UserResponseDTO> expectedUsers2 = new ArrayList<>();
        expectedUsers.add(new UserEntity(7,"Limon","Mandarino","2025-03-24"));
        expectedUsers.add(new UserEntity(8,"Corazon","De Melon","2025-03-24"));
        expectedUsers2.add(new UserResponseDTO(7,"Limon","Mandarino","2025-03-24"));
        expectedUsers2.add(new UserResponseDTO(8,"Corazon","De Melon","2025-03-24"));
        Mockito.when(userRepository.findAll()).thenReturn(expectedUsers);
        List<UserResponseDTO> actualUsers = userService.getUsers();
        Assertions.assertEquals(expectedUsers2, actualUsers);
        Mockito.verify(userRepository).findAll();

    }
}