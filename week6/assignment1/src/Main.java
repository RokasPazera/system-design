import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Employee rokasCollector = new Employee("RokasCollector", "123456789", "rokasCollector@gmail.com", new Collector());
    static Employee rokasFkDriver = new Employee("rokasFkDriver", "123456789", "rokasFkDriver@gmail.com", new ForkliftDriver());
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Employee> employees = new ArrayList<>();
    public static void main(String[] args) {
        employees.add(rokasCollector);
        employees.add(rokasFkDriver);
        showMenu();
    }
    private static void showMenu(){
        int userInput = -1;
        while (userInput != 0) {
            System.out.println("=========MENU=========");
            System.out.println("1) add employee");
            System.out.println("2) change job function of employee");
            System.out.println("3) print all salaries");
            userInput = scanner.nextInt();
                switch (userInput){
                    case 1:
                        addEmployee();
                        break;
                    case 2:
                        changeJobFunction();
                        break;
                    case 3:
                        printAllSalaries();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Invalid input");
                }
        }


    }
    private static void addEmployee() {
        JobFunction function = null;
        System.out.println("enter employee name: ");
        String name = scanner.next();

        System.out.println("enter employee phone number: ");
        String number = scanner.next();

        System.out.println("enter employee email: ");
        String email = scanner.next();

        System.out.println("enter employee job type: ");
        System.out.println("1) forklift driver");
        System.out.println("2) collector");
        int jobType = scanner.nextInt();
        if(jobType == 1) function = new ForkliftDriver();
        if(jobType == 2) function = new Collector();
        Employee newEmployee = new Employee(name, number, email, function);
        employees.add(newEmployee);
        System.out.println("New employee added successfully");
    }

    private static void changeJobFunction() {
        System.out.println("enter employee name: ");
        String name = scanner.next();

        for(Employee employee : employees){
            if(employee.getName().equals(name)){
                if(employee.getJobFunction() instanceof Collector) employee.setJobFunction(new ForkliftDriver());
                else employee.setJobFunction(new Collector());
                System.out.println("successfully changed!");
                break;
            }
        }
    }

    private static void printAllSalaries(){
        for(Employee employee : employees){
            System.out.println(employee.getName() + ": " + employee.calculateSalary());
        }
    }
}
