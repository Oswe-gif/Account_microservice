package com.example.demo.service;

import com.example.demo.endpoint.dto.AccountResponseDTO;
import com.example.demo.endpoint.dto.UserDto;
import com.example.demo.endpoint.dto.UserResponseDTO;
import com.example.demo.entity.AccountEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.state.Context;
import com.example.demo.service.state.Owner;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private Context context;

    public UserResponseDTO createUser(UserDto userDto) {
        userRepository.save(new UserEntity(userDto.getDocument(),userDto.getName(),userDto.getLastName(),userDto.getDateCreated()));
        return new UserResponseDTO(userDto.getDocument(),userDto.getName(),userDto.getLastName(),userDto.getDateCreated());
    }
    public List<AccountResponseDTO> consultAccounts(int documentUser) {
        List<AccountResponseDTO> lista= new ArrayList<AccountResponseDTO>();


        if(!this.userRepository.existsById(documentUser)){
            context.setState(new Owner());
            context.runState();
            throw new RuntimeException("El usuario que quiere consultar no existe.");
        }
        for(AccountEntity accountEntity :accountRepository.getAllAccounts(documentUser)) {
            lista.add(new AccountResponseDTO(accountEntity.getId(),accountEntity.getType(),accountEntity.getMoney(),accountEntity.getDateCreated(),accountEntity.getUser()));
        }
        return lista;
    }
    public List<UserResponseDTO> getUsers() {
        List<UserResponseDTO> lista= new ArrayList<UserResponseDTO>();
        for(UserEntity user :userRepository.findAll()) {
            lista.add(new UserResponseDTO(user.getDocument(),user.getName(),user.getLastName(),user.getDateCreated()));
        }
        return lista;
    }
}
