package dao;

import model.InvoiceDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDetailDAO {
    private Connection conn = DatabaseConnection.getConnection();
    
    /**
     * Retrieves all details for a specific invoice
     * @param invoiceId The ID of the invoice
     * @return List of InvoiceDetail objects
     */
    public List<InvoiceDetail> getDetailsByInvoiceId(int invoiceId) {
        List<InvoiceDetail> details = new ArrayList<>();
        String sql = "SELECT * FROM invoice_details WHERE invoice_id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, invoiceId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    InvoiceDetail detail = new InvoiceDetail(
                        rs.getInt("id"),
                        rs.getInt("invoice_id"),
                        rs.getInt("item_id"),
                        rs.getInt("quantity"),
                        rs.getBigDecimal("unit_price"),
                        rs.getBigDecimal("line_total")
                    );
                    details.add(detail);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return details;
    }
    
    /**
     * Adds a new invoice detail to the database
     * @param detail The InvoiceDetail object to add
     * @return The ID of the newly created detail, or -1 if failed
     */
    public int addInvoiceDetail(InvoiceDetail detail) {
        String sql = "INSERT INTO invoice_details (invoice_id, item_id, quantity, unit_price) " +
                     "VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, detail.getInvoiceId());
            stmt.setInt(2, detail.getItemId());
            stmt.setInt(3, detail.getQuantity());
            stmt.setBigDecimal(4, detail.getUnitPrice());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int detailId = generatedKeys.getInt(1);
                        detail.setDetailId(detailId);
                        
                        refreshDetailLineTotal(detail);
                        
                        return detailId;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return -1;
    }
    
    /**
     * Cập nhật lại line_total từ database cho chi tiết hóa đơn
     */
    private void refreshDetailLineTotal(InvoiceDetail detail) {
        String sql = "SELECT line_total FROM invoice_details WHERE id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, detail.getDetailId());
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    detail.setLineTotal(rs.getBigDecimal("line_total"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Updates an existing invoice detail in the database
     * @param detail The InvoiceDetail object with updated values
     * @return true if successful, false otherwise
     */
    public boolean updateInvoiceDetail(InvoiceDetail detail) {
        String sql = "UPDATE invoice_details SET invoice_id = ?, item_id = ?, quantity = ?, " +
                     "unit_price = ? WHERE id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, detail.getInvoiceId());
            stmt.setInt(2, detail.getItemId());
            stmt.setInt(3, detail.getQuantity());
            stmt.setBigDecimal(4, detail.getUnitPrice());
            stmt.setInt(5, detail.getDetailId());
            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                refreshDetailLineTotal(detail);
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    /**
     * Deletes an invoice detail from the database
     * @param detailId The ID of the detail to delete
     * @return true if successful, false otherwise
     */
    public boolean deleteInvoiceDetail(int detailId) {
        String sql = "DELETE FROM invoice_details WHERE id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, detailId);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    /**
     * Deletes all details for a specific invoice
     * @param invoiceId The ID of the invoice
     * @return true if successful, false otherwise
     */
    public boolean deleteDetailsByInvoiceId(int invoiceId) {
        String sql = "DELETE FROM invoice_details WHERE invoice_id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, invoiceId);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
} 