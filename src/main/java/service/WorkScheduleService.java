package service;

import dao.WorkScheduleDAO;
import dao.EmployeeDAO; // Assuming you have an EmployeeDAO to check if employee exists
import model.Employee; // Assuming you have an Employee model

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

public class WorkScheduleService {
    private final WorkScheduleDAO workScheduleDAO;
    private final EmployeeDAO employeeDAO; // Add EmployeeDAO

    public WorkScheduleService() {
        this.workScheduleDAO = new WorkScheduleDAO();
        this.employeeDAO = new EmployeeDAO(); // Initialize EmployeeDAO
    }

    /**
     * Adds a work schedule for an employee after basic validation.
     *
     * @param employeeId  The ID of the employee.
     * @param shiftNumber The shift number (1-4).
     * @return true if the schedule was added successfully, false otherwise.
     */
    public boolean addWorkSchedule(int employeeId, int shiftNumber) {
        // Basic Validation
        if (shiftNumber < 1 || shiftNumber > 4) {
            System.err.println("Invalid shift number. Must be between 1 and 4.");
            return false;
        }

        // Check if employee exists (optional but recommended)
        Employee employee = null;
        try {
            employee = employeeDAO.getEmployeeById(employeeId);
        } catch (SQLException e) {
            System.err.println("Database error checking employee: " + e.getMessage());
            return false;
        }
        
        if (employee == null) {
            System.err.println("Employee with ID " + employeeId + " not found.");
            return false;
        }

        // Call DAO to add the schedule
        return workScheduleDAO.addWorkSchedule(employeeId, shiftNumber);
    }

    /**
     * Retrieves work schedules for a specific date.
     *
     * @param date The date to retrieve schedules for.
     * @return A list of maps containing schedule details and employee names.
     */
    public List<Map<String, Object>> getSchedulesByDate(LocalDate date) {
        // Potentially add validation for the date if needed
        return workScheduleDAO.getSchedulesByDate(date);
    }

    /**
     * Retrieves work schedules for a specific month and year.
     *
     * @param year  The year.
     * @param month The month (1-12).
     * @return A list of maps containing schedule details and employee names for the month.
     */
    public List<Map<String, Object>> getSchedulesByMonth(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        // Potentially add validation for year/month if needed

        return workScheduleDAO.getSchedulesByDateRange(startDate, endDate);
    }

    // Add other service methods as needed
} 