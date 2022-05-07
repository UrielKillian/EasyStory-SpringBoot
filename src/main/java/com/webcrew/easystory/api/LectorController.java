package com.webcrew.easystory.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients/lector")
public class LectorController {
    private final LectorApplicationService service;
}
