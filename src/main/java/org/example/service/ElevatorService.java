package org.example.service;

import org.example.pojo.model.Elevator;
import org.example.pojo.model.Person;
import org.example.pojo.model.Request;
import org.example.pojo.model.Response;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ElevatorService {

    private TreeMap<String, Elevator> elevatorMap = new TreeMap<>();

    private void fillElevatorMap(int elevators) {
        for (int i=0; i<elevators; i++) {
            elevatorMap.put("e" + String.valueOf(i+1), new Elevator("e" + String.valueOf(i+1)));
        }
    }

    public List<Response> getLift(Request request) {
        fillElevatorMap(request.getElevators());
        List<Response> responses = new ArrayList<>();
        Response response;

        List<Person> persons = request.getPersons().stream().sorted((a, b) ->
                a.getTime()<b.getTime() ? -1 : 1).collect(Collectors.toList());

        for (Person person: persons) {
            response = new Response();
            response.setPersonId(person.getId());
            response.setElevatorId(getElevator(person));
            System.out.println("Drop " + person.getId() + " at floor no -" + person.getToFloor() +
                    " from " + response.getElevatorId());
            responses.add(response);
        }

        return responses;
    }

    private String getElevator(Person person) {
        Elevator closestElevator = null;
        int time = Integer.MAX_VALUE;
        for(Elevator elevator: elevatorMap.values()) {
            int thisTime = elevator.getTime(person.getTime(), person.getFromFloor());
            if (thisTime < time) {
                time = thisTime;
                closestElevator = elevator;
            }
        }
        closestElevator.useLift(person);
        return closestElevator.getId();
    }
}
