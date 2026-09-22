package nl.saxion.application;

import nl.saxion.domain.GradingFacade;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class Grading {

    public static void main(String[] args) {
        new Grading().run();
    }

    private void run() {
        var facade = new GradingFacade();
        var scanner = new Scanner(new BufferedInputStream(System.in));
        while (true) {
            System.out.print("Please enter the student number: ");
            var studentNumber = scanner.next();
            System.out.print("Please enter the course code: ");
            var courseCode = scanner.next();
            System.out.print("Please enter the grade: ");
            var score = scanner.next();
            facade.addGrade(studentNumber, courseCode, score);
            System.out.print("More grades to enter? (Yes/No) ");
            if (!scanner.next().equalsIgnoreCase("Yes")) {
                break;
            }
        }
        // show entered grades
        facade.printGradeOverview();
    }
}
