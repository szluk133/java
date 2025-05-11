package service;

import dao.EmployeeDAO;
import dao.SalaryDAO;
import model.Employee;
import model.Salary;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryService {

    private final SalaryDAO salaryDAO;
    private final EmployeeDAO employeeDAO;

    public SalaryService() {
        this.salaryDAO = new SalaryDAO();
        this.employeeDAO = new EmployeeDAO();
    }

    // Constructor for dependency injection (optional, good practice)
    public SalaryService(SalaryDAO salaryDAO, EmployeeDAO employeeDAO) {
        this.salaryDAO = salaryDAO;
        this.employeeDAO = employeeDAO;
    }

    /**
     * Retrieves Salary details along with associated Employee information.
     * @param employeeId The ID of the employee.
     * @return Salary object with Employee populated, or null if not found.
     */
    public Salary getEmployeeSalaryDetails(int employeeId) {
        Salary salary = salaryDAO.getSalaryByEmployeeId(employeeId);
        if (salary != null) {
            try {
                Employee employee = employeeDAO.getEmployeeById(employeeId);
                if (employee != null) {
                    salary.setEmployee(employee); // Associate employee with salary
                } else {
                    System.err.println("Service: Employee not found for ID: " + employeeId +
                                    ", but salary record exists.");
                    // Decide how to handle: return salary without employee, or return null?
                    // Returning salary without employee info might be acceptable here.
                }
            } catch (SQLException e) {
                System.err.println("Database error retrieving employee: " + e.getMessage());
                // Continue with the salary data we have, just without employee details
            }
        } else {
            System.out.println("Service: No salary record found for employee ID: " + employeeId);
        }
        return salary;
    }

    /**
     * Lấy danh sách lương của tất cả nhân viên kèm thông tin chi tiết của nhân viên
     * @return Danh sách các đối tượng Salary đã gắn với Employee
     */
    public List<Salary> getAllEmployeeSalaryDetails() {
        List<Salary> salaries = salaryDAO.getAllSalaries();
        List<Salary> result = new ArrayList<>();
        
        for (Salary salary : salaries) {
            try {
                Employee employee = employeeDAO.getEmployeeById(salary.getEmployeeId());
                if (employee != null) {
                    salary.setEmployee(employee);
                }
            } catch (SQLException e) {
                System.err.println("Database error retrieving employee ID " + 
                    salary.getEmployeeId() + ": " + e.getMessage());
                // Continue processing other salaries
            }
            result.add(salary);
        }
        
        return result;
    }

    // Add other business logic methods related to salaries here
    // e.g., calculate bonuses, update salaries based on shifts, etc.

} 