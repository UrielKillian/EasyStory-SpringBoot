package com.webcrew.easystory.application.queries;

import lombok.Data;

@Data
public class GetClientByIdQuery {
    private String clientId;

    public GetClientByIdQuery(String clientId) {
        this.clientId = clientId;
    }
}
