package com.webcrew.easystory.application.services;

import com.webcrew.easystory.application.commands.RegisterLector;
import com.webcrew.easystory.application.dtos.LectorView;
import com.webcrew.easystory.application.dtos.RegisterLectorRequest;
import com.webcrew.easystory.application.dtos.RegisterLectorResponse;
import com.webcrew.easystory.application.enums.ResultType;
import com.webcrew.easystory.application.notification.Notification;
import com.webcrew.easystory.application.notification.Result;
import com.webcrew.easystory.application.queries.GetClientByIdQuery;
import com.webcrew.easystory.application.validators.EditLectorValidator;
import com.webcrew.easystory.application.validators.RegisterLectorValidator;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class LectorApplicationService {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;
    private final RegisterLectorValidator registerPersonValidator;
    private final EditLectorValidator editPersonValidator;

    public LectorApplicationService(RegisterLectorValidator registerPersonValidator, EditLectorValidator editPersonValidator, CommandGateway commandGateway, QueryGateway queryGateway) {
        this.registerPersonValidator = registerPersonValidator;
        this.editPersonValidator = editPersonValidator;
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    public Result<RegisterLectorResponse, Notification> registerPerson(RegisterLectorRequest registerPersonRequest) throws Exception {
        Notification notification = this.registerPersonValidator.validate(registerPersonRequest);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        String clientId = UUID.randomUUID().toString();
        RegisterLector registerPerson = new RegisterLector(
                clientId,
                registerPersonRequest.getPassword().trim(),
                registerPersonRequest.getFirstName().trim(),
                registerPersonRequest.getLastName().trim(),
                registerPersonRequest.getEmail().trim(),
                registerPersonRequest.getTelephone().trim(),
                registerPersonRequest.getUsername().trim()

        );
        CompletableFuture<Object> future = commandGateway.send(registerPerson);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        RegisterLectorResponse registerPersonResponse = new RegisterLectorResponse(
                registerPerson.getId(),
                registerPerson.getUsername(),
                registerPerson.getFirstName(),
                registerPerson.getPassword(),
                registerPerson.getLastName(),
                registerPerson.getEmail(),
                registerPerson.getTelephone()
        );
        return Result.success(registerPersonResponse);
    }

    public LectorView getById(String clientId) throws Exception {
        CompletableFuture<LectorView> future = queryGateway.query(new GetClientByIdQuery(clientId), ResponseTypes.instanceOf(LectorView.class));
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        return future.get();
    }
}
