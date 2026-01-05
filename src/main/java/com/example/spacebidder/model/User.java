package com.example.spacebidder.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ClientId;
    private String clientLogin;
    private String clientName;
    private String clientSurname;
    private String clientEmail;
    private String clientPass;
    private String clientAddress;

    public User(String login, String name, String surname, String email, String pass, String address) {
        this.clientLogin = login;
        this.clientName = name;
        this.clientSurname = surname;
        this.clientEmail = email;
        this.clientPass = pass;
        this.clientAddress = address;
    }
}