package model;

import java.math.BigDecimal;

public class Salary {
    private int salaryId;
    private int employeeId;
    private BigDecimal baseSalary;
    private BigDecimal monthlySalary;
    private int shiftsWorked;
    private Employee employee; // To hold associated employee details

    // Constructors
    public Salary() {
    }

    public Salary(int salaryId, int employeeId, BigDecimal baseSalary, BigDecimal monthlySalary, int shiftsWorked) {
        this.salaryId = salaryId;
        this.employeeId = employeeId;
        this.baseSalary = baseSalary;
        this.monthlySalary = monthlySalary;
        this.shiftsWorked = shiftsWorked;
    }

    // Getters and Setters
    public int getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(int salaryId) {
        this.salaryId = salaryId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(BigDecimal baseSalary) {
        this.baseSalary = baseSalary;
    }

    public BigDecimal getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(BigDecimal monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public int getShiftsWorked() {
        return shiftsWorked;
    }

    public void setShiftsWorked(int shiftsWorked) {
        this.shiftsWorked = shiftsWorked;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Salary{" +
               "salaryId=" + salaryId +
               ", employeeId=" + employeeId +
               ", baseSalary=" + baseSalary +
               ", monthlySalary=" + monthlySalary +
               ", shiftsWorked=" + shiftsWorked +
               ", employee=" + (employee != null ? employee.getFirstName() + " " + employee.getLastName() : "N/A") +
               '}';
    }
} 