package model;

import java.time.LocalDate;

public class WorkSchedule {
    private int scheduleId;
    private int employeeId;
    private LocalDate workDate;
    private int shiftNumber; // 1=Ca Sáng sớm, 2=Ca Sáng muộn, 3=Ca Chiều sớm, 4=Ca Chiều muộn

    // Constructors
    public WorkSchedule() {
    }

    public WorkSchedule(int employeeId, LocalDate workDate, int shiftNumber) {
        this.employeeId = employeeId;
        this.workDate = workDate;
        this.shiftNumber = shiftNumber;
    }

    public WorkSchedule(int scheduleId, int employeeId, LocalDate workDate, int shiftNumber) {
        this.scheduleId = scheduleId;
        this.employeeId = employeeId;
        this.workDate = workDate;
        this.shiftNumber = shiftNumber;
    }

    // Getters and Setters
    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getWorkDate() {
        return workDate;
    }

    public void setWorkDate(LocalDate workDate) {
        this.workDate = workDate;
    }

    public int getShiftNumber() {
        return shiftNumber;
    }

    public void setShiftNumber(int shiftNumber) {
        this.shiftNumber = shiftNumber;
    }

    @Override
    public String toString() {
        return "WorkSchedule{" +
               "scheduleId=" + scheduleId +
               ", employeeId=" + employeeId +
               ", workDate=" + workDate +
               ", shiftNumber=" + shiftNumber +
               '}';
    }
} 