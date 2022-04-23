package com.webcrew.easystory.domain.values.Client;

import com.webcrew.easystory.application.notification.Notification;
import com.webcrew.easystory.application.notification.Result;
import lombok.Value;

import javax.persistence.Embeddable;

@Embeddable
@Value
public class LastName {
    private String value;
    private final static int MAX_LENGTH = 150;
    private LastName(String lastName){
        value = lastName;
    }

    protected LastName() {
        this.value = "";
    }

    public static Result<LastName, Notification>create(String lastName) {
        Notification notification = new Notification();
        lastName = lastName == null ? "" : lastName.trim();
        if (lastName.isEmpty()){
            notification.addError("Last name is required");
        }
        if(lastName.length() > MAX_LENGTH){
            notification.addError("The maximum length of last name is " + MAX_LENGTH + " characters including spaces", null);
        }
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        return Result.success(new LastName(lastName));
    }

}
