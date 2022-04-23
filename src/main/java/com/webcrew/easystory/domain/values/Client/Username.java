package com.webcrew.easystory.domain.values.Client;

import com.webcrew.easystory.application.notification.Notification;
import com.webcrew.easystory.application.notification.Result;
import lombok.Value;

import javax.persistence.Embeddable;

@Embeddable
@Value
public class Username {
    private String value;
    private final static int MAX_LENGTH = 150;

    private Username(String address) {
        value = address;
    }

    protected Username() {
        this.value = "";
    }

    public static Result<Username, Notification>create(String username){
        Notification notification = new Notification();
        username = username == null ? "" : username.trim();
        if (username.isEmpty()){
            notification.addError("username is required", null);
            return Result.failure(notification);
        }
        if (username.length() > MAX_LENGTH) {
            notification.addError("The maximum lenght of an address is " + MAX_LENGTH + " characters including spaces", null);
        }
        if(notification.hasErrors()) {
            return Result.failure(notification);
        }
        return Result.success(new Username(username));
    }
}
