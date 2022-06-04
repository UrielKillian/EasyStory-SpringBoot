package com.webcrew.easystory.command.application.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class EditLector {
    @TargetAggregateIdentifier
    private String id;
    private String username;
    private String firstName;
    private String password;
    private String lastName;
    private String email;
    private String telephone;
}
