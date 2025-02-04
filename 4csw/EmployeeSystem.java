import java.util.ArrayList;

abstract class Employee {
    private String name;
    
    public Employee(String name) {
        this.name = name;
    }
    
    public abstract void work();
    
    public double getSalary() {
        double salary = 50000; 
        System.out.println(name + "'s salary: " + salary);
        return salary;
    }
    
    public String getName() {
        return name;
    }
}

class HRManager extends Employee {
    private ArrayList<String> employees;
    
    public HRManager(String name) {
        super(name);
        employees = new ArrayList<>();
    }
    
    @Override
    public void work() {
        System.out.println(getName() + " is managing HR-related work...");
    }
    
    public void addEmployee(String employeeName) {
        employees.add(employeeName);
        System.out.println(getName() + " added new employee: " + employeeName);
    }
    
    public void listEmployees() {
        System.out.println(getName() + "'s employees:");
        if (employees.isEmpty()) {
            System.out.println("No employees added.");
            return;
        }
        for (String employee : employees) {
            System.out.println("- " + employee);
        }
    }
}

public class EmployeeSystem {
    public static void main(String[] args) {
        HRManager hr = new HRManager("Alice");
        hr.work();
        hr.getSalary();
        hr.addEmployee("John");
        hr.addEmployee("Emma");
        hr.addEmployee("Jack");
        hr.addEmployee("Sophia");
        hr.listEmployees();
    }
}