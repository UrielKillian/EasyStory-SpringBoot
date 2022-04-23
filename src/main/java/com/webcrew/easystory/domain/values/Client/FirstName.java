package com.webcrew.easystory.domain.values.Client;

import com.webcrew.easystory.application.notification.Notification;
import com.webcrew.easystory.application.notification.Result;
import lombok.Value;

import javax.persistence.Embeddable;

@Embeddable
@Value
public class FirstName {
    private String value;
    private final static int MAX_LENGTH = 200;
    private FirstName(String firstName) {
        value = firstName;
    }

    protected FirstName() {
        this.value = "";
    }

    public static Result<FirstName, Notification>create(String password) {
        Notification notification = new Notification();
        if (password.isEmpty()) {
            notification.addError("First name is required");
            return Result.failure(notification);
        }
        if(password.length() < MAX_LENGTH) {
            notification.addError("The maximum length of first name is " + MAX_LENGTH + " characters including spaces", null);
        }
        if (notification.hasErrors()){
            return Result.failure(notification);
        }
        return Result.success(new FirstName(password));
    }
}
