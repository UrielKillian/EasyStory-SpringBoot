package com.webcrew.easystory.application.dtos;
import lombok.Value;

@Value
public class RegisterClientRequest {
    private String id;
    private String username;
    private String firstName;
    private String password;
    private String lastName;
    private String email;
    private String telephone;
}