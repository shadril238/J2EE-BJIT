public class Employee {
    private String name;
    private int age;
    private String department;
    private double salary;
    public Employee(String name, int age, String department, double salary){
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    // Setters and getters
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getAge(){
        return this.age;
    }
    public void setAge(int age){
        this.age = age;
    }
    public String getDepartment(){
        return this.department;
    }
    public void setDepartment(String department){
        this.department = department;
    }
    public double getSalary(){
        return this.salary;
    }
    public void setSalary(double salary){
        this.salary = salary;
    }
    @Override
    public String toString() {
        return String.format("Employee Name: %s, Employee Age: %d, Employee Department: %s, Employee Salary: %.2f",
                this.name, this.age, this.department, this.salary);
    }
}
