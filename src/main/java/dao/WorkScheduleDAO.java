package dao;

import model.WorkSchedule;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkScheduleDAO {
    private Connection conn = DatabaseConnection.getConnection();

    /**
     * Adds a new work schedule entry to the database for the current date.
     *
     * @param employeeId  The ID of the employee.
     * @param shiftNumber The shift number (1-4).
     * @return true if the schedule was added successfully, false otherwise.
     */
    public boolean addWorkSchedule(int employeeId, int shiftNumber) {
        String sql = "INSERT INTO work_schedules (employee_id, work_date, shift_number) VALUES (?, ?, ?)";
        LocalDate today = LocalDate.now(); // Get current date

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            stmt.setDate(2, Date.valueOf(today)); // Convert LocalDate to sql.Date
            stmt.setInt(3, shiftNumber);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error adding work schedule: " + e.getMessage());
            // Consider more specific error handling or logging
            return false;
        }
    }

    /**
     * Retrieves work schedules for a specific date, including employee names.
     *
     * @param date The date to retrieve schedules for.
     * @return A list of maps, where each map contains schedule details and employee name.
     */
    public List<Map<String, Object>> getSchedulesByDate(LocalDate date) {
        List<Map<String, Object>> schedules = new ArrayList<>();
        String sql = "SELECT ws.schedule_id, ws.employee_id, ws.work_date, ws.shift_number, " +
                     "e.first_name, e.last_name " +
                     "FROM work_schedules ws " +
                     "JOIN employees e ON ws.employee_id = e.employee_id " +
                     "WHERE ws.work_date = ? " +
                     "ORDER BY ws.shift_number, e.last_name, e.first_name";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(date));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> scheduleInfo = new HashMap<>();
                    scheduleInfo.put("scheduleId", rs.getInt("schedule_id"));
                    scheduleInfo.put("employeeId", rs.getInt("employee_id"));
                    scheduleInfo.put("workDate", rs.getDate("work_date").toLocalDate());
                    scheduleInfo.put("shiftNumber", rs.getInt("shift_number"));
                    scheduleInfo.put("employeeFirstName", rs.getString("first_name"));
                    scheduleInfo.put("employeeLastName", rs.getString("last_name"));
                    schedules.add(scheduleInfo);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving work schedules by date: " + e.getMessage());
            // Return an empty list or handle the error as appropriate
        }
        return schedules;
    }

    /**
     * Retrieves work schedules within a specific date range, including employee names.
     *
     * @param startDate The start date of the range (inclusive).
     * @param endDate   The end date of the range (inclusive).
     * @return A list of maps, where each map contains schedule details and employee name.
     */
    public List<Map<String, Object>> getSchedulesByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Map<String, Object>> schedules = new ArrayList<>();
        String sql = "SELECT ws.schedule_id, ws.employee_id, ws.work_date, ws.shift_number, " +
                     "e.first_name, e.last_name " +
                     "FROM work_schedules ws " +
                     "JOIN employees e ON ws.employee_id = e.employee_id " +
                     "WHERE ws.work_date BETWEEN ? AND ? " +
                     "ORDER BY ws.work_date, ws.shift_number, e.last_name, e.first_name";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(startDate));
            stmt.setDate(2, Date.valueOf(endDate));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> scheduleInfo = new HashMap<>();
                    scheduleInfo.put("scheduleId", rs.getInt("schedule_id"));
                    scheduleInfo.put("employeeId", rs.getInt("employee_id"));
                    scheduleInfo.put("workDate", rs.getDate("work_date").toLocalDate());
                    scheduleInfo.put("shiftNumber", rs.getInt("shift_number"));
                    scheduleInfo.put("employeeFirstName", rs.getString("first_name"));
                    scheduleInfo.put("employeeLastName", rs.getString("last_name"));
                    schedules.add(scheduleInfo);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving work schedules by date range: " + e.getMessage());
            // Return an empty list or handle the error as appropriate
        }
        return schedules;
    }

    // You can add other methods here later if needed, e.g.,
    // - getSchedulesByEmployee(int employeeId)
    // - updateSchedule(WorkSchedule schedule)
    // - deleteSchedule(int scheduleId)
} 