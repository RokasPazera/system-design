package nl.saxion.domain;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Student extends Person{

    private final static ArrayList<Student> allStudents = new ArrayList<>();

    public static Stream<Student> getAllStudents() {
        return allStudents.stream();
    }

    private final String number;

    @Override
    public void save() {
        allStudents.add(this);
    }

    public Student(String number, String name) {
        super(name);
        this.number = number;
    }


    public String getNumber() {
        return number;
    }

    @Override
    public String getName() {
        return name;
    }
}
