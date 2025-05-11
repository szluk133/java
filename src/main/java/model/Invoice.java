package model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Invoice {
    private int invoiceId;
    private int customerId;
    private int employeeId;
    private Timestamp invoiceDate;
    private BigDecimal totalAmount;
    private BigDecimal tax;
    private BigDecimal discount;
    private String status; // 'due', 'partial', 'paid'
    
    // Thông tin bổ sung (không lưu trong DB)
    private Customer customer;
    private Employee employee;

    // Constructors
    public Invoice() {
    }

    public Invoice(int invoiceId, int customerId, int employeeId, Timestamp invoiceDate, 
                  BigDecimal totalAmount, BigDecimal tax, BigDecimal discount, String status) {
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.invoiceDate = invoiceDate;
        this.totalAmount = totalAmount;
        this.tax = tax;
        this.discount = discount;
        this.status = status;
    }

    // Getters and Setters
    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Timestamp getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Timestamp invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public boolean isPaid() {
        return "paid".equals(status);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", customerId=" + customerId +
                ", employeeId=" + employeeId +
                ", invoiceDate=" + invoiceDate +
                ", totalAmount=" + totalAmount +
                ", tax=" + tax +
                ", discount=" + discount +
                ", status='" + status + '\'' +
                '}';
    }
} 