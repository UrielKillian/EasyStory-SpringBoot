package com.webcrew.easystory.command.application.validators;

import com.webcrew.easystory.command.application.dtos.EditLectorRequest;
import com.webcrew.easystory.command.application.notification.Notification;
import com.webcrew.easystory.command.domain.entities.Lector;
import com.webcrew.easystory.command.infrastructure.repositories.LectorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class EditLectorValidator {
    private final LectorRepository lectorRepository;

    public EditLectorValidator(LectorRepository lectorRepository) {
        this.lectorRepository = lectorRepository;
    }

    public Notification validate(EditLectorRequest editLectorRequest){
        Notification notification = new Notification();
        String clientId = editLectorRequest.getId().trim();
        if(clientId.isEmpty()){
            notification.addError("Client Id Is Required");
        }
        Optional<Lector>lectorOptional = lectorRepository.findById(UUID.fromString(clientId));
        if (lectorOptional.isPresent()){
            notification.addError("Client Not Found");
            return notification;
        }
        String firstName = editLectorRequest.getFirstName().trim();
        if(firstName.isEmpty()) {
            notification.addError("First Name Is Required");
        }
        String lastName = editLectorRequest.getLastName().trim();
        if(lastName.isEmpty()) {
            notification.addError("Last Name Is Required");
        }
        String email = editLectorRequest.getEmail().trim();
        if(email.isEmpty()) {
            notification.addError("Email Is Required");
        }
        String phone = editLectorRequest.getTelephone().trim();
        if(phone.isEmpty()) {
            notification.addError("Telephone Is Required");
        }
        if(notification.hasErrors()){
            return notification;
        }
        lectorOptional = lectorRepository.findByEmail(email);
        if(lectorOptional.isPresent()){
            notification.addError("Email Already Exists");
        }
        return notification;
    }
}
