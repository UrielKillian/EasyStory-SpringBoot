package com.webcrew.easystory.application.dtos;

import lombok.Data;

@Data
public class LectorView {
    private String clientId;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private String createdAt;
}
