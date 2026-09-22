package nl.saxion.domain;

public class GradingFacade {

    public GradingFacade() {
        populateDomain();
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
    }

    public void addGrade(String studentNumber, String courseCode, String score) {
        var student = Student.getAllStudents()
                .filter(s -> s.getNumber().equals(studentNumber))
                .findFirst()
                .get();
        var course = Course.getAllCourses()
                .filter(c -> c.getName().equals(courseCode))
                .findFirst()
                .get();
        new Grade(student, course, score).save();
    }

    public void printGradeOverview() {
        final String format = "%-20s %-10s %-25s %-5s\n";
        System.out.printf(format, "Student", "Course", "Course teacher", "Score");
        System.out.printf(format, "=".repeat(20), "=".repeat(10), "=".repeat(25), "=".repeat(5));
        Grade.getAllGrades().forEach(grade -> {
            var student = grade.getStudent();
            var course = grade.getCourse();
            var teacher = course.getTeacher();
            System.out.printf(format, student.getName(), course.getName(), teacher.getName(), grade.getScore());
        });
    }
}
