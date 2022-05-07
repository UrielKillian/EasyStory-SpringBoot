package com.webcrew.easystory.api;

import com.webcrew.easystory.application.dtos.LectorView;
import com.webcrew.easystory.application.dtos.RegisterLectorRequest;
import com.webcrew.easystory.application.dtos.RegisterLectorResponse;
import com.webcrew.easystory.application.notification.Notification;
import com.webcrew.easystory.application.notification.Result;
import com.webcrew.easystory.application.services.LectorApplicationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients/lector")
public class LectorController {
    private final LectorApplicationService lectorApplicationService;

    public LectorController(LectorApplicationService lectorApplicationService) {
        this.lectorApplicationService = lectorApplicationService;
    }

    @PostMapping(path= "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registerPerson(@RequestBody RegisterLectorRequest registerPersonRequest) {
        try {
            Result<RegisterLectorResponse, Notification> result = lectorApplicationService.registerPerson(registerPersonRequest);
            if (result.isSuccess()) {
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch(Exception e) {
            return ApiController.serverError();
        }
    }

    @GetMapping(path = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    //@ApiOperation(value = "Get client person by id", response = PersonView.class)
    public ResponseEntity<Object> getById(@PathVariable("id") String id) {
        try {
            LectorView lectorView = lectorApplicationService.getById(id);
            return ApiController.ok(lectorView);
        } catch(Exception e) {
            e.printStackTrace();
            return ApiController.serverError();
        }
    }
}
