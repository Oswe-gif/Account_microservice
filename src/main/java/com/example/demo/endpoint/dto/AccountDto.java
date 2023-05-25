package com.example.demo.endpoint.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Generated
@NoArgsConstructor
public class AccountDto {
    private int id;
    private String type;
    private int money=0;
    private String dateCreated;
    private int user;

}