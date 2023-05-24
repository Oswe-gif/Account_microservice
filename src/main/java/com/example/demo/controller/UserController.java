package com.example.demo.controller;

import com.example.demo.controller.dto.AccountResponseDTO;
import com.example.demo.controller.dto.UserDto;
import com.example.demo.controller.dto.UserResponseDTO;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class UserController {
    private UserService userService;
    @PostMapping(path = "/user/savings-user")
    public UserResponseDTO createUser(@RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }
    @GetMapping(path = "/user/check-accounts/{idDocument}")
    public List<AccountResponseDTO> getAllAccounts(@PathVariable int idDocument) {
        return userService.consultAccounts(idDocument);
    }
    @GetMapping(path = "/user/all-Users")
    public List<UserResponseDTO> getAllUsers() {
        return userService.getUsers();
    }
}
