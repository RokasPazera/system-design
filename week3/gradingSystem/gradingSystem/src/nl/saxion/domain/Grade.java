package nl.saxion.domain;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Grade {

    private final static ArrayList<Grade> allGrades = new ArrayList<>();

    public static Stream<Grade> getAllGrades() {
        return allGrades.stream();
    }

    private final Student student;

    private final Course course;

    private final String score;

    public Grade(Student student, Course course, String score) {
        this.student = student;
        this.course = course;
        this.score = score;
    }

    public void save() {
        allGrades.add(this);
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public String getScore() {
        return score;
    }
}
