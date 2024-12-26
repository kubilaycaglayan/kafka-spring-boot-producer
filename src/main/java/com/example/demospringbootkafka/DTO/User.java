package com.example.demospringbootkafka.DTO;

import lombok.Data;

@Data
public class User {
    private int id;
    private String name;
    private String department;
    private String email;
}
