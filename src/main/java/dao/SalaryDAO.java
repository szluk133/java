package dao;

import model.Salary;
import model.Employee; // Needed if we populate Employee object within Salary

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SalaryDAO {

    // Fetches salary details for a specific employee
    public Salary getSalaryByEmployeeId(int employeeId) {
        String sql = "SELECT s.salary_id, s.employee_id, s.base_salary, s.monthly_salary, s.shifts_worked " +
                     "FROM salaries s WHERE s.employee_id = ?";
        Salary salary = null;
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.err.println("DAO: Failed to get database connection.");
            return null;
        }

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, employeeId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                salary = new Salary();
                salary.setSalaryId(rs.getInt("salary_id"));
                salary.setEmployeeId(rs.getInt("employee_id"));
                salary.setBaseSalary(rs.getBigDecimal("base_salary"));
                salary.setMonthlySalary(rs.getBigDecimal("monthly_salary"));
                salary.setShiftsWorked(rs.getInt("shifts_worked"));

                // Optionally, fetch and set the associated Employee object
                // EmployeeDAO employeeDAO = new EmployeeDAO();
                // Employee employee = employeeDAO.getEmployeeById(employeeId);
                // salary.setEmployee(employee);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching salary by employee ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
             // Connection closing should be handled by the DatabaseConnection utility
             // or at a higher level (e.g., Service layer) if managing transactions.
        }
        return salary;
    }

    /**
     * Lấy danh sách lương của tất cả nhân viên
     * @return Danh sách các đối tượng Salary
     */
    public List<Salary> getAllSalaries() {
        String sql = "SELECT salary_id, employee_id, base_salary, monthly_salary, shifts_worked FROM salaries";
        List<Salary> salaries = new ArrayList<>();
        Connection conn = DatabaseConnection.getConnection();
        
        if (conn == null) {
            System.err.println("DAO: Failed to get database connection.");
            return salaries;
        }
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            while (rs.next()) {
                Salary salary = new Salary();
                salary.setSalaryId(rs.getInt("salary_id"));
                salary.setEmployeeId(rs.getInt("employee_id"));
                salary.setBaseSalary(rs.getBigDecimal("base_salary"));
                salary.setMonthlySalary(rs.getBigDecimal("monthly_salary"));
                salary.setShiftsWorked(rs.getInt("shifts_worked"));
                salaries.add(salary);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all salaries: " + e.getMessage());
            e.printStackTrace();
        }
        
        return salaries;
    }

    // Add other necessary methods like updateSalary, addSalaryRecord etc. if needed

} 