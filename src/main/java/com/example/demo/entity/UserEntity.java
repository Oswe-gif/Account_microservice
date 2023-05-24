package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class UserEntity {
    @Id
    @Column(name ="document", nullable = false)
    private Integer document;
    @Column(name ="name", nullable = false)
    private String name;
    @Column(name ="last_name", nullable = false)
    private String lastName;
    @Column(name ="date_created", nullable = false)
    private String dateCreated;

}