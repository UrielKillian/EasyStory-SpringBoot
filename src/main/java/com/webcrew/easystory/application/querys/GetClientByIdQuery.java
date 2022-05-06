package com.webcrew.easystory.application.querys;

import lombok.Data;

@Data
public class GetClientByIdQuery {
    private String clientId;

    public GetClientByIdQuery(String clientId) {
        this.clientId = clientId;
    }
}
