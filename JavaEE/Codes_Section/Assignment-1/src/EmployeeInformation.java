//shadril238
import java.util.Arrays;
public class EmployeeInformation {
    public static void main(String[] args) {
        String data = "John:30:HR:50000,Alice:28:Finance:60000,Bob:35:Engineering:75000,Emily:32:HR:55000";

        EmployeeInformation employeeInformation = new EmployeeInformation();
        System.out.println("Total Employees : " + employeeInformation.calculateTotalNumberOfEmployees(data));
        System.out.println("Average Age : " + employeeInformation.calculateAverageAge(data));
        System.out.println("Employee with Highest Salary : " + employeeInformation.employeeWithTheHighestSalary(data));
        System.out.println("Sorted Names : " + employeeInformation.sortedEmployeeNames(data));

    }
    // Calculate the total number of employees.
    private int calculateTotalNumberOfEmployees(String data){
        String[] employees = data.split(",");
        return employees.length;
    }
    // Calculate the average age of all employees.
    private double calculateAverageAge(String data){
        int totalEmployeeAge = 0;
        String[] employees = data.split(",");
        for(String employee : employees){
            String[] employeeData = employee.split(":");
            int employeeAge = Integer.parseInt(employeeData[1]);
            totalEmployeeAge += employeeAge;
        }
        return (double)totalEmployeeAge / employees.length;
    }
    //Find the employee with the highest salary.
    private String employeeWithTheHighestSalary(String data){
        String[] employees = data.split(",");
        double highestSalary = -1;
        String highestSalaryEmployee = "";
        for(String employee : employees){
            String[] employeeData = employee.split(":");
            double employeeSalary = Double.parseDouble(employeeData[3]);
            if(employeeSalary > highestSalary){
                highestSalary = employeeSalary;
                highestSalaryEmployee = employeeData[0];
            }
        }
        return highestSalaryEmployee;
    }
    //Create a new string containing all employees' names sorted alphabetically.
    private String sortedEmployeeNames(String data){
        String[] employees = data.split(",");
        Arrays.sort(employees);
        StringBuilder sortedEmployeeNames = new StringBuilder();
        String delimiter = "";
        for(String employee : employees){
            String[] employeeData = employee.split(":");
            sortedEmployeeNames.append(delimiter).append(employeeData[0]);
            delimiter=",";
        }
        return  sortedEmployeeNames.toString();
    }
}