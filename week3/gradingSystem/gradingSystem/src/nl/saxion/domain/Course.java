package nl.saxion.domain;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Course {

    private final static ArrayList<Course> allCourses = new ArrayList<>();

    public static Stream<Course> getAllCourses() {
        return allCourses.stream();
    }

    private final String name;

    private final String year;

    private final String quartile;

    public Teacher getTeacher() {
        return teacher;
    }

    private final Teacher teacher;

    public Course(String name, String year, String quartile, Teacher teacher) {
        this.name = name;
        this.year = year;
        this.quartile = quartile;
        this.teacher = teacher;
    }

    public void save() {
        allCourses.add(this);
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public String getQuartile() {
        return quartile;
    }

}
