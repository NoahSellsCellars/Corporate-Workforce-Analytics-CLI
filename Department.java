import java.util.ArrayList;
import java.util.List;

public class Department {
    private String departmentName;
    private List<Employee> departmentLeads;
    private List<Employee> employees;

    public Department() {
        this.departmentLeads = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Employee> getDepartmentLeads() {
        return departmentLeads;
    }

    public void addDepartmentLead(Employee departmentLead) {
        this.departmentLeads.add(departmentLead);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    /**
     * Finds employee(s) with the minimum salary in the department.
     */
    public String getLowestSalary() {
        if (employees.isEmpty()) return "No employees.";

        // Step 1: Find the minimum salary value
        int minSalary = Integer.MAX_VALUE;
        for (Employee emp : employees) {
            if (emp.getSalary() < minSalary) {
                minSalary = emp.getSalary();
            }
        }

        // Step 2: Collect all employees with that salary
        StringBuilder sb = new StringBuilder();
        for (Employee emp : employees) {
            if (emp.getSalary() == minSalary) {
                sb.append(emp.getNameAndSalary()).append("\n");
            }
        }
        return sb.toString().trim();
    }

    /**
     * Returns a formatted list of employees with tenure below the specified threshold.
     */
    public String getEmployeesUnderTenure(int years) {
        StringBuilder sb = new StringBuilder();
        boolean found = false;

        for (Employee emp : employees) {
            if (emp.getTenure() < years) {
                sb.append(emp.getInfo()).append("\n");
                found = true;
            }
        }

        if (!found) {
            return "No employees with less than " + years + " years of tenure.";
        }
        return sb.toString().trim();
    }

    public double averageSalary() {
        if (employees.isEmpty()) return 0.0;
        
        double total = 0.0;
        for (Employee emp : employees) {
            total += emp.getSalary();
        }
        return total / employees.size();
    }
}