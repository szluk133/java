package model;

import java.time.LocalDate;

public class Employee {
    private int employeeId;
    private String firstName;
    private String lastName;
    private String position;
    private String address;
    private String phone;
    private String email;
    private LocalDate hireDate;
    public Employee() {
    }

    // Constructor đầy đủ
    public Employee(int employeeId, String firstName, String lastName, String position,
                    String address, String phone, String email, LocalDate hireDate) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.hireDate = hireDate;
    }

    // Getters & Setters
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    // Phương thức toString
    @Override
    public String toString() {
        return String.format("ID: %d | Tên: %s %s | Chức vụ: %s | Email: %s | SĐT: %s | Ngày vào làm: %s",
                employeeId, firstName, lastName, position, email, phone, hireDate);
    }
}