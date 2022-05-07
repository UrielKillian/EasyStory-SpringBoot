package com.webcrew.easystory.application.services;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;

@Component
public class LectorApplicationService {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;
    private final RegisterPersonValidator registerPersonValidator;
    private final EditPersonValidator editPersonValidator;

    public PersonApplicationService(RegisterPersonValidator registerPersonValidator, EditPersonValidator editPersonValidator, CommandGateway commandGateway, QueryGateway queryGateway) {
        this.registerPersonValidator = registerPersonValidator;
        this.editPersonValidator = editPersonValidator;
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    public Result<RegisterPersonResponse, Notification> registerPerson(RegisterPersonRequest registerPersonRequest) throws Exception {
        Notification notification = this.registerPersonValidator.validate(registerPersonRequest);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        String clientId = UUID.randomUUID().toString();
        RegisterPerson registerPerson = new RegisterPerson(
                clientId,
                registerPersonRequest.getDni().trim(),
                registerPersonRequest.getFirstName().trim(),
                registerPersonRequest.getLastName().trim(),
                registerPersonRequest.getAddress().trim(),
                registerPersonRequest.getEmail().trim(),
                registerPersonRequest.getPhone().trim()
        );
        CompletableFuture<Object> future = commandGateway.send(registerPerson);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        RegisterPersonResponse registerPersonResponse = new RegisterPersonResponse(
                registerPerson.getId(),
                registerPerson.getDni(),
                registerPerson.getFirstName(),
                registerPerson.getLastName(),
                registerPerson.getAddress(),
                registerPerson.getEmail(),
                registerPerson.getPhone()
        );
        return Result.success(registerPersonResponse);
    }

    public PersonView getById(String clientId) throws Exception {
        CompletableFuture<PersonView> future = queryGateway.query(new GetClientPersonByIdQuery(clientId), ResponseTypes.instanceOf(PersonView.class));
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        return future.get();
    }
}
