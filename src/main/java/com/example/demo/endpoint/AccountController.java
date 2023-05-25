package com.example.demo.endpoint;
import com.example.demo.endpoint.dto.*;
import com.example.demo.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@NoArgsConstructor
@RestController
public class AccountController {
    private AccountService accountService;
    @PostMapping(path = "/account/savings-account")
    public AccountResponseDTO createAccount(@RequestBody AccountDto accountDto) {
        return accountService.insertAccount(accountDto);
    }

    @GetMapping(path = "/account/check-balance/idAccount")
    public AccountResponseDTO checkBalance(@RequestBody AccountDto accountDto) {
        return accountService.checkBalance(accountDto.getId());
    }

}
