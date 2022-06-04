package com.webcrew.easystory.command.application.handlers.commands;

import com.webcrew.easystory.command.application.commands.RegisterLector;
import com.webcrew.easystory.command.application.notification.Notification;
import com.webcrew.easystory.command.application.notification.Result;
import com.webcrew.easystory.command.domain.entities.Lector;
import com.webcrew.easystory.command.domain.values.AuditTrail;
import com.webcrew.easystory.command.domain.values.Client.*;
import com.webcrew.easystory.command.infrastructure.repositories.LectorRepository;
import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegisterLectorHandler {
    private LectorRepository lectorRepository;

    public RegisterLectorHandler(LectorRepository lectorRepository) {
        this.lectorRepository = lectorRepository;
    }

    @CommandHandler
    public Result<Lector, Notification> handle(RegisterLector registerLector) {
        Notification notification = new Notification();
        ClientId clientId = ClientId.of(UUID.fromString(registerLector.getId()));
        Result<Username,Notification>usernameResult = Username.create(registerLector.getUsername());
        if(!usernameResult.isSuccess()) {
            notification.addError(usernameResult.getFailure().errorMessage());
        }
        Result<Password, Notification>passwordResult = Password.create(registerLector.getPassword());
        if(!passwordResult.isSuccess()) {
            notification.addError(passwordResult.getFailure().errorMessage());

        }
        Result<FirstName, Notification>firstNameResult = FirstName.create(registerLector.getFirstName());
        if(!firstNameResult.isSuccess()) {
            notification.addError(firstNameResult.getFailure().errorMessage());
        }
        Result<LastName, Notification>lastNameResult = LastName.create(registerLector.getLastName());
        if(!lastNameResult.isSuccess()) {
            notification.addError(lastNameResult.getFailure().errorMessage());
        }
        Result<Email, Notification>emailResult = Email.create(registerLector.getEmail());
        if(!emailResult.isSuccess()) {
            notification.addError(emailResult.getFailure().errorMessage());
        }
        Result<Telephone, Notification>phoneResult = Telephone.create(registerLector.getTelephone());
        if(!phoneResult.isSuccess()) {
            notification.addError(phoneResult.getFailure().errorMessage());
        }
        Result<AuditTrail, Notification>auditTrailResult = AuditTrail.create(UUID.randomUUID());
        if(!auditTrailResult.isSuccess()) {
            notification.addError(auditTrailResult.getFailure().errorMessage());
        }
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        Lector lector = new Lector(
                clientId,
                usernameResult.getSuccess(),
                passwordResult.getSuccess(),
                firstNameResult.getSuccess(),
                lastNameResult.getSuccess(),
                emailResult.getSuccess(),
                phoneResult.getSuccess(),
                auditTrailResult.getSuccess()
                );
        lectorRepository.save(lector);
        return Result.success(lector);
    }
}
