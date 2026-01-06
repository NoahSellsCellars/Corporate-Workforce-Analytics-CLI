import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final String CSV_FILE_PATH = "employee_data.csv";    //File path to employee data file

    // ---------------------------------------------------
    // Core Business Logic
    // ---------------------------------------------------

    /**
     * Identifies the employee(s) with the longest tenure across the entire organization.
     */
    public static ArrayList<Employee> findLongestTenuredEmployees(ArrayList<Department> departments) {
        ArrayList<Employee> longestTenureEmployees = new ArrayList<>();
        int maxTenure = -1;

        for (Department department : departments) {
            for (Employee employee : department.getEmployees()) {
                if (employee.getTenure() > maxTenure) {
                    maxTenure = employee.getTenure();
                    longestTenureEmployees.clear();
                    longestTenureEmployees.add(employee);
                } else if (employee.getTenure() == maxTenure) {
                    longestTenureEmployees.add(employee);
                }
            }
        }
        return longestTenureEmployees;
    }

    /**
     * Generates a report of average salaries per department.
     */
    public static void printAverageSalaryReport(ArrayList<Department> departments) {
        System.out.println("\n--- Department Salary Analysis ---");
        for (Department department : departments) {
            System.out.print("Department: " + department.getDepartmentName());
            if (department.getEmployees().isEmpty()) {
                System.out.println(" - [No Data]");
            } else {
                System.out.printf(" | Average Salary: $%.2f%n", department.averageSalary());
            }
        }
    }

    /**
     * Generates a report of leadership distribution.
     */
    public static void printLeadershipReport(ArrayList<Department> departments) {
        System.out.println("\n--- Leadership Structure ---");
        for (Department department : departments) {
            System.out.println("Department: " + department.getDepartmentName() + 
                               " | Leads: " + department.getDepartmentLeads().size());
        }
    }

    // ---------------------------------------------------
    // Main Execution
    // ---------------------------------------------------

    public static void main(String[] args) {
        ArrayList<Department> departments = new ArrayList<>();
        File dataFile = new File(CSV_FILE_PATH);

        // 1. Data Ingestion
        try (Scanner scanner = new Scanner(dataFile)) {
            if (scanner.hasNextLine()) scanner.nextLine(); // Skip CSV Header

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");

                if (fields.length >= 7) { 
                    // Parse Fields
                    String name = fields[0].trim() + " " + fields[1].trim();
                    String deptName = fields[2].trim();
                    String position = fields[3].trim();
                    int salary = Integer.parseInt(fields[4].trim());
                    int tenure = Integer.parseInt(fields[5].trim());
                    boolean isLead = fields[6].trim().equalsIgnoreCase("yes");

                    // Build Employee Object
                    Employee emp = new Employee();
                    emp.setName(name);
                    emp.setDepartment(deptName);
                    emp.setPosition(position);
                    emp.setSalary(salary);
                    emp.setTenure(tenure);
                    emp.setDepartmentLead(isLead);

                    // Assign to Department (Create if not exists)
                    Department targetDept = null;
                    for (Department d : departments) {
                        if (d.getDepartmentName().equalsIgnoreCase(deptName)) {
                            targetDept = d;
                            break;
                        }
                    }

                    if (targetDept == null) {
                        targetDept = new Department();
                        targetDept.setDepartmentName(deptName);
                        departments.add(targetDept);
                    }

                    targetDept.addEmployee(emp);
                    if (isLead) {
                        targetDept.addDepartmentLead(emp);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: Data file '" + CSV_FILE_PATH + "' not found.");
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Error parsing data: " + e.getMessage());
        }

        // 2. Report Generation
        System.out.println("=========================================");
        System.out.println("    CORPORATE WORKFORCE ANALYTICS");
        System.out.println("=========================================");

        // Report: Lowest Salaries (Anonymized for Analytics)
        System.out.println("\n--- Minimum Salary Baselines ---");
        for (Department dept : departments) {
            System.out.println(dept.getDepartmentName() + ": " + dept.getLowestSalary());
        }

        // Report: Retention Risk (Tenure < 4 Years)
        System.out.println("\n--- Retention Watchlist (< 4 Years Tenure) ---");
        for (Department dept : departments) {
            System.out.println(dept.getDepartmentName() + ": " + dept.getEmployeesUnderTenure(4));
        }

        // Report: Average Salaries
        printAverageSalaryReport(departments);

        // Report: Longest Tenure
        System.out.println("\n--- Longest Serving Employees ---");
        for (Employee emp : findLongestTenuredEmployees(departments)) {
            System.out.println(emp.getInfo());
        }

        // Report: Leadership
        printLeadershipReport(departments);
    }
}