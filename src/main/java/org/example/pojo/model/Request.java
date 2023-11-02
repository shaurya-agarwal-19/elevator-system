package org.example.pojo.model;

import java.util.List;

public class Request {
    private int floors;
    private int elevators;
    private int people;
    private List<Person> persons;

    public int getFloors() {
        return floors;
    }

    public int getElevators() {
        return elevators;
    }

    public int getPeople() {
        return people;
    }

    public List<Person> getPersons() {
        return persons;
    }
}
