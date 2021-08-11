package com.douzone.client.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;
    private String username;
    private String password;
    private String name;
    private String phoneNumber;
}
