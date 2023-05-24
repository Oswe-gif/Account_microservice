package com.example.demo.controller.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;

@Data
@AllArgsConstructor
@Generated
public class AccountDto {
    private int id;
    private String type;
    private int money=0;
    private String dateCreated;
    private int user;

}