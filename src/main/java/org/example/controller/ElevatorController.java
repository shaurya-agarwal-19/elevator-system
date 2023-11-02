package org.example.controller;

import org.example.exception.error.Error;
import org.example.pojo.model.Request;
import org.example.pojo.model.Response;
import org.example.service.ElevatorService;
import org.example.validator.ElevatorValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("")
public class ElevatorController {

    private ElevatorValidator elevatorValidator;
    private ElevatorService elevatorService;

    public ElevatorController(ElevatorValidator elevatorValidator, ElevatorService elevatorService) {
        this.elevatorValidator = elevatorValidator;
        this.elevatorService = elevatorService;
    }

    @GetMapping("/lift")
    public ResponseEntity<List<Response>> getLift(@RequestBody Request request) {
        List<Error> errors = elevatorValidator.validate(request);
        if (!errors.isEmpty()) {
            return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
        }

        List<Response> response = elevatorService.getLift(request);

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
