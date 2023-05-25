package com.example.demo.endpoint.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Generated
@NoArgsConstructor
public class UserResponseDTO {
    private Integer document;
    private String name;
    private String lastName;
    private String dateCreated;
}
