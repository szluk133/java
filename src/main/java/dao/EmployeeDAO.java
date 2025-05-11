package dao;

import model.Employee;
import java.sql.*;
import java.sql.Date;
import java.util.*;
public class EmployeeDAO {
    private Connection conn = DatabaseConnection.getConnection();
    public List<Employee> getAllEmployees() throws SQLException {
        String sql = "SELECT * FROM employees";
        List<Employee> employees = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setEmployeeId(rs.getInt("employee_id"));
                emp.setFirstName(rs.getString("first_name"));
                emp.setLastName(rs.getString("last_name"));
                emp.setPosition(rs.getString("position"));
                emp.setAddress(rs.getString("address"));
                emp.setPhone(rs.getString("phone"));
                emp.setEmail(rs.getString("email"));
                employees.add(emp);
            }
        }
        return employees;
    }

    public void addEmployee(Employee employee) throws SQLException {
        String sql = "INSERT INTO employees (first_name, last_name, position, address, phone, email, hire_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, employee.getFirstName());
            stmt.setString(2, employee.getLastName());
            stmt.setString(3, employee.getPosition());
            stmt.setString(4, employee.getAddress());
            stmt.setString(5, employee.getPhone());
            stmt.setString(6, employee.getEmail());
            stmt.setDate(7, Date.valueOf(employee.getHireDate()));
            stmt.executeUpdate();
        }
    }

    public void deleteEmployee(int id) throws SQLException {
        String sql = "DELETE FROM employees WHERE employee_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void updateEmployee(Employee emp) throws SQLException {
        String sql = "UPDATE employees SET first_name=?, last_name=?, position=?, address=?, phone=?, email=?, hire_date=? WHERE employee_id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, emp.getFirstName());
            stmt.setString(2, emp.getLastName());
            stmt.setString(3, emp.getPosition());
            stmt.setString(4, emp.getAddress());
            stmt.setString(5, emp.getPhone());
            stmt.setString(6, emp.getEmail());
            if (emp.getHireDate() != null) {
                stmt.setDate(7, Date.valueOf(emp.getHireDate()));
            } else {
                stmt.setDate(7, null);
            }

            stmt.setInt(8, emp.getEmployeeId());
            stmt.executeUpdate();
        }
    }

    public Employee getEmployeeById(int id) throws SQLException {
        String sql = "SELECT * FROM employees WHERE employee_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Employee emp = new Employee();
                emp.setEmployeeId(rs.getInt("employee_id"));
                emp.setFirstName(rs.getString("first_name"));
                emp.setLastName(rs.getString("last_name"));
                emp.setPosition(rs.getString("position"));
                emp.setAddress(rs.getString("address"));
                emp.setPhone(rs.getString("phone"));
                emp.setEmail(rs.getString("email"));
                return emp;
            }
        }
        return null;
    }
}