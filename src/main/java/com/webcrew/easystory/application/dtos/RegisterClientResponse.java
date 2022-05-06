package com.webcrew.easystory.application.dtos;
import lombok.Data;
import lombok.Value;

@Data
public class RegisterClientResponse {

    private String id;
    private String username;
    private String firstName;
    private String password;
    private String lastName;
    private String email;
    private String telephone;




}
