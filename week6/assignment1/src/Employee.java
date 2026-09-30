public class Employee {
    private String name;
    private String phoneNumber;
    private String emailAddress;
    private JobFunction jobFunction;

    public Employee(String name, String phoneNumber, String emailAddress, JobFunction jobFunction) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.jobFunction = jobFunction;
    }

    public void setJobFunction(JobFunction jobFunction) {
        this.jobFunction = jobFunction;
    }
    public JobFunction getJobFunction() {
        return this.jobFunction;
    }

    public String getName() {
        return this.name;
    }

    public double calculateSalary() {
        return jobFunction.calculateSalary();
    }
}
