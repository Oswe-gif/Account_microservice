package com.example.demo.endpoint;
import com.example.demo.endpoint.dto.*;
import com.example.demo.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.Generated;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Generated
@RestController
public class AccountController {
    private AccountService accountService;
    @PostMapping(path = "/account/savings-account")
    public void createAccount(@RequestBody AccountDto accountDto) {
        accountService.insertAccount(accountDto);
    }

    @GetMapping(path = "/account/check-balance/{idAccount}")
    public AccountResponseDTO checkBalance(@PathVariable int idAccount) {
        return accountService.checkBalance(idAccount);
    }

}
