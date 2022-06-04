package com.webcrew.easystory.command.domain.values.Client;

import com.webcrew.easystory.command.application.notification.Notification;
import com.webcrew.easystory.command.application.notification.Result;
import lombok.Value;

import javax.persistence.Embeddable;

@Embeddable
@Value
public class Email {
    private String value;
    private final static int MAX_LENGTH = 200;

    private Email(String email) {
        value = email;
    }

    protected Email() {
        this.value = "";
    }

    public static Result<Email, Notification>create(String email) {
        Notification notification = new Notification();
        email = email == null ? "" : email.trim();
        if (email.isEmpty()){
            notification.addError("Email is required");
            return Result.failure(notification);
        }
        if (email.length() > MAX_LENGTH){
            notification.addError("The maximum length of email is " + MAX_LENGTH + " characters including spaces", null);
        }
        if(notification.hasErrors()) {
            return Result.failure(notification);
        }
        return Result.success(new Email(email));
    }

}
