package com.webcrew.easystory.query;

import lombok.Data;

@Data
public class GetClientByIdQuery {
    private String clientId;

    public GetClientByIdQuery(String clientId) {
        this.clientId = clientId;
    }
}
