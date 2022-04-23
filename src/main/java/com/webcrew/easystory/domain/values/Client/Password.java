package com.webcrew.easystory.domain.values.Client;

import com.webcrew.easystory.application.notification.Notification;
import com.webcrew.easystory.application.notification.Result;
import lombok.Value;

import javax.persistence.Embeddable;

@Embeddable
@Value
public class Password {
    private String value;
    private final static int MAX_LENGTH = 200;

    private Password(String password) { value = password; }

    protected Password() { this.value = ""; }

    public static Result<Password, Notification>create(String password) {
        Notification notification = new Notification();
        password = password ==  null ? "" : password.trim();
        if(password.isEmpty()){
            notification.addError("password is required");
            return Result.failure(notification);
        }
        if(password.length() > MAX_LENGTH) {
            notification.addError("The maximum lenght of an address is " + MAX_LENGTH + " characters including spaces", null);
        }
        if(notification.hasErrors()){
            return Result.failure(notification);
        }
        return Result.success(new Password(password));
    }

}
