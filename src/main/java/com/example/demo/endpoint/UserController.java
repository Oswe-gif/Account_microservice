package com.example.demo.endpoint;

import com.example.demo.endpoint.dto.AccountResponseDTO;
import com.example.demo.endpoint.dto.UserDto;
import com.example.demo.endpoint.dto.UserResponseDTO;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Generated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Generated
@RestController
public class UserController {
    private UserService userService;
    @PostMapping(path = "/user/savings-user")
    public void createUser(@RequestBody UserDto userDto){
        userService.createUser(userDto);
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
