package model;

import java.sql.Timestamp;

public class User {
    private int userId;
    private String username;
    private String passwordHash;
    private int roleId;
    private Integer employeeId; // Có thể null
    private Integer customerId; // Có thể null
    private Timestamp createdAt;
    
    public User() {
    }
    
    public User(int userId, String username, String passwordHash, int roleId, Integer employeeId, Integer customerId, Timestamp createdAt) {
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.roleId = roleId;
        this.employeeId = employeeId;
        this.customerId = customerId;
        this.createdAt = createdAt;
    }
    
    // Constructor cũ để tương thích ngược
    public User(int userId, String username, String passwordHash, int roleId, Integer employeeId, Timestamp createdAt) {
        this(userId, username, passwordHash, roleId, employeeId, null, createdAt);
    }
    
    // Constructor for creating new user
    public User(String username, String passwordHash, int roleId, 
                Integer employeeId, Integer customerId) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.roleId = roleId;
        this.employeeId = employeeId;
        this.customerId = customerId;
    }
    
    // Getters và Setters
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPasswordHash() {
        return passwordHash;
    }
    
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    
    public int getRoleId() {
        return roleId;
    }
    
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    
    public Integer getEmployeeId() {
        return employeeId;
    }
    
    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
    
    public Integer getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
