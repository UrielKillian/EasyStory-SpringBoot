package com.webcrew.easystory.command.domain.values.Client;

import com.webcrew.easystory.command.application.notification.Notification;
import com.webcrew.easystory.command.application.notification.Result;
import lombok.Value;

import javax.persistence.Embeddable;

@Embeddable
@Value
public class Telephone {
    private String value;
        private final static int MAX_LENGTH = 150;

    private Telephone(String telephone) {
        value = telephone;
    }

    protected Telephone() {
        this.value = "";
    }

    public static Result<Telephone, Notification>create(String telephone) {
        Notification notification = new Notification();
        telephone = telephone == null ? "" : telephone.trim();
        if(telephone.isEmpty()){
            notification.addError("telephone is required");
        }
        if(telephone.length() > MAX_LENGTH){
            notification.addError("The maximum length of telephone is " + MAX_LENGTH + " characters including spaces", null);
        }
        if(notification.hasErrors()){
            return Result.failure(notification);
        }
        return Result.success(new Telephone(telephone));
    }

}
