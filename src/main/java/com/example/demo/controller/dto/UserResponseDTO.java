package com.example.demo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;

@Data
@AllArgsConstructor
@Generated
public class UserResponseDTO {
    private Integer document;
    private String name;
    private String lastName;
    private String dateCreated;
}
