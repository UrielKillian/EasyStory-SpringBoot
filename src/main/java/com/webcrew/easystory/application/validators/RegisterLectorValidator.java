package com.webcrew.easystory.application.validators;

import com.webcrew.easystory.application.dtos.RegisterClientRequest;
import com.webcrew.easystory.application.dtos.RegisterLectorRequest;
import com.webcrew.easystory.application.notification.Notification;
import com.webcrew.easystory.domain.entities.Client;
import com.webcrew.easystory.domain.entities.Lector;
import com.webcrew.easystory.infrastructure.repositories.ClientRepository;
import com.webcrew.easystory.infrastructure.repositories.LectorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RegisterLectorValidator {
    private final LectorRepository lectorRepository;

    public RegisterLectorValidator(LectorRepository lectorRepository) {
        this.lectorRepository = lectorRepository;
    }

    public Notification validate(RegisterLectorRequest registerClientRequest) {
        Notification notification = new Notification();
        String Id = registerClientRequest.getId() != null ? registerClientRequest.getId().trim() : "";
        if (Id.isEmpty()) {
            notification.addError("Client id is required");
        }
        String firstName = registerClientRequest.getFirstName() != null ? registerClientRequest.getFirstName().trim()
                : "";
        if (firstName.isEmpty()) {
            notification.addError("Client firstname is required");
        }
        String lastName = registerClientRequest.getLastName() != null ? registerClientRequest.getLastName().trim() : "";
        if (lastName.isEmpty()) {
            notification.addError("Client lastname is required");
        }
        String address = registerClientRequest.getUsername() != null ? registerClientRequest.getUsername().trim() : "";
        if (address.isEmpty()) {
            notification.addError("Client username is required");
        }
        String email = registerClientRequest.getEmail() != null ? registerClientRequest.getEmail().trim() : "";
        if (email.isEmpty()) {
            notification.addError("Client email is required");
        }
        String phone = registerClientRequest.getTelephone() != null ? registerClientRequest.getTelephone().trim() : "";
        if (phone.isEmpty()) {
            notification.addError("Client telephone is required");
        }
        if (notification.hasErrors()) {
            return notification;
        }
        Optional<Lector> lectorOptional = lectorRepository.findByEmail(email);
        if (lectorOptional.isPresent()) {
            notification.addError("Client id is taken");
        }
        return notification;
    }
}
