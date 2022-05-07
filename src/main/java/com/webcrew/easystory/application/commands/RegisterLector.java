package com.webcrew.easystory.application.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class RegisterLector {
    @TargetAggregateIdentifier
    private String id;
    private String username;
    private String firstName;
    private String password;
    private String lastName;
    private String email;
    private String telephone;


}
