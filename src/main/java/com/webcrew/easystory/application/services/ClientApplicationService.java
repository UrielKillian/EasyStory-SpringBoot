package com.webcrew.easystory.application.services;

import com.webcrew.easystory.application.commands.RegisterLector;
import com.webcrew.easystory.application.dtos.RegisterClientRequest;
import com.webcrew.easystory.application.dtos.RegisterClientResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;
import com.webcrew.easystory.application.enums.ResultType;
import com.webcrew.easystory.application.notification.Notification;
import com.webcrew.easystory.application.notification.Result;
import com.webcrew.easystory.application.validators.RegisterClientValidator;


import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class ClientApplicationService {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    private final RegisterClientValidator registerclientValidator;


    public ClientApplicationService (RegisterClientValidator registerClientValidator, CommandGateway commandGateway, QueryGateway queryGateway){
        this.registerclientValidator = registerClientValidator;
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }


    public Result<RegisterClientResponse, Notification> registerClient(RegisterClientRequest registerClientRequest) throws Exception {
        Notification notification = this.registerclientValidator.validate(registerClientRequest);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        String clientId = UUID.randomUUID().toString();
        RegisterLector registerClient = new RegisterLector(
                clientId,
                registerClientRequest.getUsername().trim(),
                registerClientRequest.getId().trim(),
                registerClientRequest.getFirstName().trim(),
                registerClientRequest.getLastName().trim(),
                registerClientRequest.getEmail().trim(),
                registerClientRequest.getTelephone().trim()
        );
        CompletableFuture<Object> future = commandGateway.send(registerClient);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        RegisterClientResponse registerClientResponse = new RegisterClientResponse();

        return Result.success(new RegisterClientResponse());

    }

}
