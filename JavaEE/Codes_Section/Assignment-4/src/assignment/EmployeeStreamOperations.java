package assignment;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeStreamOperations implements EmployeeOperations{
    private List<Employee> employeeList = null;
    public EmployeeStreamOperations(){
        employeeList = new ArrayList<Employee>();
    }
    public static void main(String[] args) {

        EmployeeStreamOperations empStreamOps = new EmployeeStreamOperations();

        // Initialize some employees
        empStreamOps.employeeList = empStreamOps.initEmployee();

        // Filter employees from the IT department who are older than 25.
        Optional<List<Employee>> filterITEmployeesOlderThan25 = empStreamOps.filterEmployeesByDepartmentAndAge(empStreamOps.employeeList, "IT", 25);
        if(filterITEmployeesOlderThan25.isPresent()){
            List<Employee> employees = filterITEmployeesOlderThan25.get();
            System.out.println("Employees of IT department older than 25:- ");
            employees.forEach(e -> System.out.println(empStreamOps.getEmployeeDetails(e)));
        }
        else{
            System.out.println("No Employees found! ");
        }

        // Calculate the average salary of all employees
        System.out.println("\nThe average salary of all employees:- " + empStreamOps.avgSalaryOfEmployees(empStreamOps.employeeList));

        // Find the highest paid employee's details
        Optional<Employee> highestPaidEmployee = empStreamOps.highestPaidEmployees(empStreamOps.employeeList);
        if(highestPaidEmployee.isPresent()){
            Employee employee = highestPaidEmployee.get();
            System.out.println("\nHighest Paid assignment.Employee: ");
            System.out.println(empStreamOps.getEmployeeDetails(employee));
        }
        else{
            System.out.println("No Employees found! ");
        }

        // Sort employees by age in ascending order and then by salary in descending order
        Optional<List<Employee>> sortedEmployees = empStreamOps.sortedEmployees(empStreamOps.employeeList);
        if(sortedEmployees.isPresent()){
            List<Employee> employees = sortedEmployees.get();
            System.out.println("\nSorted employees by age in ascending order and then by salary in descending order: ");
            employees.forEach(e -> System.out.println(empStreamOps.getEmployeeDetails(e)));
        }
        else{
            System.out.println("No Employees found! ");
        }
    }

    // Initialize some employees
    private List<Employee> initEmployee(){
        return List.of(
                new Employee("Shifat", 27, "IT", 35000),
                new Employee("Shadril", 23, "HR", 30000),
                new Employee("Avijit", 24, "IT", 37000),
                new Employee("Apon", 25, "MIS", 29000),
                new Employee("Pritom", 30, "IT", 38000),
                new Employee("Rochi", 28, "HR", 33000),
                new Employee("Gourob", 35, "MIS", 42000),
                new Employee("Hassan", 35, "IT", 45000)
        );
    }
    // Filter employees based on Department (==) and Age ( > )
    private Optional<List<Employee>> filterEmployeesByDepartmentAndAge(List<Employee> employeeList, String department, int age) {
        List<Employee> filteredEmployee = employeeList.stream()
                .filter( e -> e.getDepartment().equals(department) && e.getAge() > age)
                .collect(Collectors.toList());

        return filteredEmployee.isEmpty() ? Optional.empty() : Optional.of(filteredEmployee);
    }

    // Calculate the average salary of all employees
    private double avgSalaryOfEmployees(List<Employee> employeeList){
        return employeeList.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0);
    }

    // Find the highest paid employee's details
    private Optional<Employee> highestPaidEmployees(List<Employee> employeeList){
        return employeeList.stream()
                .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
    }

    // Sort employees by age in ascending order and then by salary in descending order
    private Optional<List<Employee>> sortedEmployees(List<Employee> employeeList){
        List<Employee> sortedEmployees = employeeList.stream()
                .sorted(Comparator.comparing(Employee::getAge).thenComparing(Comparator.comparingDouble(Employee::getSalary).reversed()))
                .collect(Collectors.toList());

        return sortedEmployees.isEmpty() ? Optional.empty() : Optional.of(sortedEmployees);
    }
}
