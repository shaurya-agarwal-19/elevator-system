package org.example.validator;

import org.example.exception.error.Error;
import org.example.pojo.model.Request;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ElevatorValidator {

    public List<Error> validate(Request request) {
        List<Error> errors = new ArrayList<>();

        if(request.getFloors() < 1) {
            errors.add(new Error(400, "Hey, total floors should not be less that 1"));
        }
        if(request.getElevators() < 1) {
            errors.add(new Error(400, "Hey, total elevators should not be less that 1"));
        }
        if(request.getPeople() < 1) {
            errors.add(new Error(400, "Hey, total number of people should not be less that 1"));
        }
        if(request.getPersons().size() != request.getPeople()) {
            errors.add(new Error(400, "Oops! Total number of people are not matching with the total number " +
                    "of persons' data. Can you please check."));
        }

        return errors;
    }
}
