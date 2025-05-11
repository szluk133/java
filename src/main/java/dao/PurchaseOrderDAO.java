package dao;

import model.PurchaseOrder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderDAO {
    private Connection getConnection() {
        return DatabaseConnection.getConnection();
    }

    public List<PurchaseOrder> getAllPurchaseOrders() throws SQLException {
        List<PurchaseOrder> orders = new ArrayList<>();
        Connection conn = null;
        
        try {
            conn = getConnection();
            String sql = "SELECT * FROM purchase_orders";
            
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    PurchaseOrder po = new PurchaseOrder(
                            rs.getInt("po_id"),
                            rs.getInt("supplier_id"),
                            rs.getDate("expected_date"),
                            rs.getString("status")
                    );
                    orders.add(po);
                }
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return orders;
    }

    public int createPurchaseOrder(PurchaseOrder order) throws SQLException {
        Connection conn = null;
        int generatedId = -1;
        
        try {
            conn = getConnection();
            String sql = "INSERT INTO purchase_orders (supplier_id, expected_date, status) VALUES (?, ?, ?)";
            
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, order.getSupplierId());
                stmt.setDate(2, order.getExpectedDate());
                stmt.setString(3, order.getStatus());
                
                int affectedRows = stmt.executeUpdate();
                
                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            generatedId = generatedKeys.getInt(1);
                        }
                    }
                }
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        
        return generatedId;
    }

    public void updatePurchaseOrder(PurchaseOrder order) throws SQLException {
        Connection conn = null;
        
        try {
            conn = getConnection();
            String sql = "UPDATE purchase_orders SET supplier_id=?, expected_date=?, status=? WHERE po_id=?";
            
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, order.getSupplierId());
                stmt.setDate(2, order.getExpectedDate());
                stmt.setString(3, order.getStatus());
                stmt.setInt(4, order.getPoId());
                
                stmt.executeUpdate();
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void deletePurchaseOrder(int id) throws SQLException {
        Connection conn = null;
        
        try {
            conn = getConnection();
            
            // First, delete all order details
            String detailsSql = "DELETE FROM purchase_order_details WHERE po_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(detailsSql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
            
            // Then delete the order itself
            String orderSql = "DELETE FROM purchase_orders WHERE po_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(orderSql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public PurchaseOrder getPurchaseOrderById(int id) throws SQLException {
        Connection conn = null;
        PurchaseOrder po = null;
        
        try {
            conn = getConnection();
            String sql = "SELECT * FROM purchase_orders WHERE po_id = ?";
            
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                
                if (rs.next()) {
                    po = new PurchaseOrder();
                    po.setPoId(rs.getInt("po_id"));
                    po.setSupplierId(rs.getInt("supplier_id"));
                    po.setExpectedDate(rs.getDate("expected_date"));
                    po.setStatus(rs.getString("status"));
                }
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        
        return po;
    }
}
