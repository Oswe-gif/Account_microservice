package com.example.demo.controller;
import com.example.demo.controller.dto.*;
import com.example.demo.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class AccountController {
    private AccountService accountService;
    @PostMapping(path = "/account/savings-account")
    public AccountResponseDTO createAccount(@RequestBody AccountDto accountDto) {
        return accountService.insertAccount(accountDto);
    }

    @GetMapping(path = "/account/check-balance/{idAccount}")
    public AccountResponseDTO checkBalance(@PathVariable int idAccount) {
        return accountService.checkBalance(idAccount);
    }

}
