package nl.saxion.application;

import nl.saxion.domain.*;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class Grading {

    public static void main(String[] args) {
        new Grading().run();
    }

    private void populateDomain() {
        new Student("01", "Jaina").save();
        new Student("02", "Arthas").save();
        new Student("03", "Velen").save();
        new Student("04", "Fenris").save();
        Teacher t1 = new Teacher("Kevin Wilmink", 100000);
        Teacher t2 = new Teacher("Dick Heijink", 100000);
        Teacher t3 = new Teacher("Jan Jaap", 100000);
        new Course("sdp", "2021", "2", t1).save();
        new Course("oop", "2020", "3", t2).save();
        new Course("ip01", "2022", "1", t3).save();
        new Coach("Rokas Pazera").save();
        new Coach("Rimas Pa").save();
    }

    private void getDOB() {
        System.out.println("dateOfBirth:");
        for (Student s : Student.getAllStudents().toList()) {
            System.out.println(s.getDateOfBirth());
        }
        for (Teacher t : Teacher.getAllTeachers().toList()) {
            System.out.println(t.getDateOfBirth());
        }
        for (Coach c : Coach.getAllCoaches().toList()) {
            System.out.println(c.getDateOfBirth());
        }
    }

    private void helpingPeople(){
        System.out.println("helpPerson:");
        var coach = Coach.getAllCoaches().findFirst().get();
        var student = Student.getAllStudents().findFirst().get();
        var teacher = Teacher.getAllTeachers().findFirst().get();
        System.out.println(coach.helpPerson(student));
        System.out.println(coach.helpPerson(teacher));
    }

    private void payingPeople(){
        System.out.println("pay:");
        for (Teacher t : Teacher.getAllTeachers().toList()) {
            System.out.println(t.getName() + " is paid: " + t.pay());
        }
        for (Coach c : Coach.getAllCoaches().toList()) {
            System.out.println(c.getName() + " is paid: " + c.pay());
        }
    }

    private void run() {
        // populate domain with some objects for testing purposes
        populateDomain();
        getDOB();
        helpingPeople();
        payingPeople();
        var scanner = new Scanner(new BufferedInputStream(System.in));
        while (true) {
            System.out.print("Please enter the student number: ");
            var studentNumber = scanner.next();
            // look up student with given student number
            var student =
                    Student.getAllStudents()
                            .filter(s -> s.getNumber().equals(studentNumber))
                            .findFirst()
                            .get();
            System.out.print("Please enter the course code: ");
            var courseCode = scanner.next();
            // look up course with given course code
            var course =
                    Course.getAllCourses()
                            .filter(c -> c.getName().equals(courseCode))
                            .findFirst()
                            .get();
            System.out.print("Please enter the grade: ");
            var score = scanner.next();
            // create new grade and save it 'persistently'
            new Grade(student, course, score).save();
            System.out.print("More grades to enter? (Yes/No) ");
            if (!scanner.next().equalsIgnoreCase("Yes")) {
                break;
            }
        }
        // show entered grades
        final String format = "%-20s %-10s %-25s %-5s\n";
        System.out.printf(format, "Student", "Course","Course teacher", "Score", "");
        System.out.printf(format, "=".repeat(20), "=".repeat(10),"=".repeat(25), "=".repeat(5));
        Grade.getAllGrades().forEach(grade -> {
            var student = grade.getStudent();
            var course = grade.getCourse();
            var teacher = course.getTeacher();
            System.out.printf(format, student.getName(), course.getName(),teacher.getName(), grade.getScore());
        });
    }
}
