package nl.saxion.domain;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Teacher extends Person{

    private double salary;

    private final static ArrayList<Teacher> allTeachers = new ArrayList<>();

    public static Stream<Teacher> getAllTeachers() {
        return allTeachers.stream();
    }

    public Teacher(String name, double salary) {
        super(name);
        this.salary = salary;
    }


    @Override
    public String getName() {
        return "Ir. " + name;
    }

    @Override
    public void save() {
        allTeachers.add(this);
    }
}
