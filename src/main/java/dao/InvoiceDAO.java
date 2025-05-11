package dao;

import model.Invoice;
import model.InvoiceDetail;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDAO {
    private Connection conn = DatabaseConnection.getConnection();

    /**
     * Retrieves all invoices from the database
     * @return List of Invoice objects
     */
    public List<Invoice> getAllInvoices() {
        List<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT * FROM invoices";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Invoice invoice = new Invoice(
                    rs.getInt("invoice_id"),
                    rs.getInt("customer_id"),
                    rs.getInt("employee_id"),
                    rs.getTimestamp("invoice_date"),
                    rs.getBigDecimal("total_amount"),
                    rs.getBigDecimal("tax"),
                    rs.getBigDecimal("discount"),
                    rs.getString("status")
                );
                invoices.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return invoices;
    }
    
    /**
     * Lấy tất cả hóa đơn của một khách hàng theo customer_id
     * @param customerId ID của khách hàng
     * @return Danh sách các hóa đơn của khách hàng
     */
    public List<Invoice> getInvoicesByCustomerId(int customerId) {
        List<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT * FROM invoices WHERE customer_id = ? ORDER BY invoice_date DESC";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Invoice invoice = new Invoice(
                        rs.getInt("invoice_id"),
                        rs.getInt("customer_id"),
                        rs.getInt("employee_id"),
                        rs.getTimestamp("invoice_date"),
                        rs.getBigDecimal("total_amount"),
                        rs.getBigDecimal("tax"),
                        rs.getBigDecimal("discount"),
                        rs.getString("status")
                    );
                    invoices.add(invoice);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return invoices;
    }
    
    /**
     * Retrieves an invoice by its ID
     * @param invoiceId The ID of the invoice to retrieve
     * @return The Invoice object if found, null otherwise
     */
    public Invoice getInvoiceById(int invoiceId) {
        String sql = "SELECT * FROM invoices WHERE invoice_id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, invoiceId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Invoice(
                        rs.getInt("invoice_id"),
                        rs.getInt("customer_id"),
                        rs.getInt("employee_id"),
                        rs.getTimestamp("invoice_date"),
                        rs.getBigDecimal("total_amount"),
                        rs.getBigDecimal("tax"),
                        rs.getBigDecimal("discount"),
                        rs.getString("status")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    /**
     * Adds a new invoice to the database
     * @param invoice The Invoice object to add
     * @return The ID of the newly created invoice, or -1 if failed
     */
    public int addInvoice(Invoice invoice) {
        String sql = "INSERT INTO invoices (customer_id, employee_id, invoice_date, total_amount, status) " +
                     "VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, invoice.getCustomerId());
            stmt.setInt(2, invoice.getEmployeeId());
            stmt.setTimestamp(3, invoice.getInvoiceDate());
            stmt.setBigDecimal(4, invoice.getTotalAmount());
            stmt.setString(5, invoice.getStatus());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int invoiceId = generatedKeys.getInt(1);
                        Invoice created = getInvoiceById(invoiceId);
                        if (created != null) {
                            invoice.setInvoiceId(created.getInvoiceId());
                            invoice.setTax(created.getTax());
                            invoice.setDiscount(created.getDiscount());
                        }
                        return invoiceId;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return -1;
    }
    
    /**
     * Updates an existing invoice in the database
     * @param invoice The Invoice object with updated values
     * @return true if successful, false otherwise
     */
    public boolean updateInvoice(Invoice invoice) {
        String sql = "UPDATE invoices SET customer_id = ?, employee_id = ?, invoice_date = ?, " +
                     "total_amount = ?, status = ? WHERE invoice_id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, invoice.getCustomerId());
            stmt.setInt(2, invoice.getEmployeeId());
            stmt.setTimestamp(3, invoice.getInvoiceDate());
            stmt.setBigDecimal(4, invoice.getTotalAmount());
            stmt.setString(5, invoice.getStatus());
            stmt.setInt(6, invoice.getInvoiceId());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    /**
     * Deletes an invoice from the database
     * @param invoiceId The ID of the invoice to delete
     * @return true if successful, false otherwise
     */
    public boolean deleteInvoice(int invoiceId) {
        String sql = "DELETE FROM invoices WHERE invoice_id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, invoiceId);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    /**
     * Updates the status of an invoice
     * @param invoiceId The ID of the invoice to update
     * @param status The new status value ('due', 'partial', 'paid')
     * @return true if successful, false otherwise
     */
    public boolean updateInvoiceStatus(int invoiceId, String status) {
        String sql = "UPDATE invoices SET status = ? WHERE invoice_id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, invoiceId);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
} 