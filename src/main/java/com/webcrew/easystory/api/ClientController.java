package com.webcrew.easystory.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.webcrew.easystory.application.dtos.*;
import com.webcrew.easystory.application.services.ClientApplicationService;
import com.webcrew.easystory.application.notification.Notification;
import com.webcrew.easystory.application.notification.Result;


@RestController
@RequestMapping("/api/client")
public class ClientController {
    private final ClientApplicationService clientApplicationService;

    public ClientController(ClientApplicationService  clientApplicationService){
        this.clientApplicationService = clientApplicationService;
    }

    public ResponseEntity<Object>registerClient(@RequestBody RegisterClientRequest registerClientRequest){
        try {
            Result<RegisterClientResponse, Notification>result = clientApplicationService.registerClient(registerClientRequest);
            if (result.isSuccess()) {
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }
}
