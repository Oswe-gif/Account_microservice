package com.example.demo.service;
import com.example.demo.endpoint.dto.AccountDto;
import com.example.demo.endpoint.dto.AccountResponseDTO;
import com.example.demo.entity.AccountEntity;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.state.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService {
    private AccountRepository accountRepository;
    private UserRepository userRepository;
    private Context context;

    public AccountResponseDTO insertAccount(AccountDto accountDto){
        int size=accountRepository.getAllAccounts(accountDto.getUser()).size();
        if(!(size<4)) {
            context.setState(new Account());
            context.runState();
            throw new RuntimeException("Excede el numero de cuentas registradas.");
        }
        if(!this.userRepository.existsById(accountDto.getUser())){
            context.setState(new Owner());
            context.runState();
            throw new RuntimeException("El usuario no existe.");
        }
        accountRepository.save(new AccountEntity(accountDto.getId(),accountDto.getType(),accountDto.getMoney(),accountDto.getDateCreated(),accountDto.getUser()));
        return new AccountResponseDTO(accountDto.getId(),accountDto.getType(),accountDto.getMoney(),accountDto.getDateCreated(),accountDto.getUser());
    }

    public AccountResponseDTO checkBalance(int accountNumber){
        if(!this.accountRepository.existsById(accountNumber)){
            context.setState(new Account());
            context.runState();
            throw new RuntimeException("La cuenta a la que quiere consultar no existe.");
        }
        AccountEntity accountEntity = accountRepository.findById(accountNumber).orElse(new AccountEntity());
        return new AccountResponseDTO(accountEntity.getId(),accountEntity.getType(),accountEntity.getMoney(),accountEntity.getDateCreated(),accountEntity.getUser());
    }

}