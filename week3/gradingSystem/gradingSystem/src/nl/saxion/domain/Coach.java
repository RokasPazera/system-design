package nl.saxion.domain;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Coach extends Person implements Payable {

    private final static ArrayList<Coach> allCoaches = new ArrayList<>();

    public static Stream<Coach> getAllCoaches() {
        return allCoaches.stream();
    }

    public Coach(String name) {
        super(name);
    }

    public String helpPerson(Person person) {
        return "Person " + person.getName() + " has been helped!";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void save() {
        allCoaches.add(this);
    }

    @Override
    public String getDateOfBirth() {
        return "Coach, date of birth: " + dateOfBirth;
    }

    @Override
    public int pay() {
        return 3000;
    }
}
