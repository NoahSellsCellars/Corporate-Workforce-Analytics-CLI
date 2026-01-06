public class Employee {
    private String name;
    private String department;
    private String position;
    private int salary;
    private int tenure;
    private boolean isDepartmentLead;

    // Constructors
    public Employee() {}

    public Employee(String name, String department, String position, int salary, int tenure, boolean isDepartmentLead) {
        this.name = name;
        this.department = department;
        this.position = position;
        this.salary = salary;
        this.tenure = tenure;
        this.isDepartmentLead = isDepartmentLead;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public int getSalary() { return salary; }
    public void setSalary(int salary) { this.salary = salary; }

    public int getTenure() { return tenure; }
    public void setTenure(int tenure) { this.tenure = tenure; }

    public boolean isDepartmentLead() { return isDepartmentLead; }
    public void setDepartmentLead(boolean isDepartmentLead) { this.isDepartmentLead = isDepartmentLead; }

    // Business Views
    public String getInfo() {
        return String.format("Name: %s | Dept: %s | Salary: $%,d | Tenure: %d yrs | Lead: %s",
                name, department, salary, tenure, (isDepartmentLead ? "Yes" : "No"));
    }

    public String getNameAndSalary() {
        return String.format("%s ($%,d)", name, salary);
    }
    
    @Override
    public String toString() {
        return getInfo();
    }
}