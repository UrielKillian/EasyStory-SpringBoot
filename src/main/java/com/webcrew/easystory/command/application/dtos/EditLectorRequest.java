package com.webcrew.easystory.command.application.dtos;

import lombok.Data;

@Data
public class EditLectorRequest {
    private String id;
    private String username;
    private String firstName;
    private String password;
    private String lastName;
    private String email;
    private String telephone;
}
