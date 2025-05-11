package dao;

import java.sql.*;
import java.util.*;

import model.PurchaseOrderDetail;

public class PurchaseOrderDetailDAO {
    
    private Connection getConnection() {
        return DatabaseConnection.getConnection();
    }

    public boolean addDetail(PurchaseOrderDetail detail) throws SQLException {
        Connection conn = null;
        
        try {
            conn = getConnection();
            String sql = "INSERT INTO purchase_order_details (po_id, item_id, quantity, unit_price, received_date) VALUES (?, ?, ?, ?, ?)";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, detail.getPoId());
            stmt.setInt(2, detail.getItemId());
            stmt.setInt(3, detail.getQuantity());
            stmt.setDouble(4, detail.getUnitPrice());
            stmt.setDate(5, detail.getReceivedDate());
            
            int result = stmt.executeUpdate();
            stmt.close();
            
            return result > 0;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public List<PurchaseOrderDetail> getDetailsByOrderId(int poId) throws SQLException {
        List<PurchaseOrderDetail> list = new ArrayList<>();
        Connection conn = null;
        
        try {
            conn = getConnection();
            String sql = "SELECT * FROM purchase_order_details WHERE po_id = ?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, poId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PurchaseOrderDetail detail = new PurchaseOrderDetail();
                detail.setPodId(rs.getInt("pod_id"));
                detail.setPoId(rs.getInt("po_id"));
                detail.setItemId(rs.getInt("item_id"));
                detail.setQuantity(rs.getInt("quantity"));
                detail.setUnitPrice(rs.getDouble("unit_price"));
                detail.setReceivedDate(rs.getDate("received_date"));
                
                list.add(detail);
            }
            
            rs.close();
            stmt.close();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        
        return list;
    }

    public boolean deleteDetail(int podId) throws SQLException {
        Connection conn = null;
        
        try {
            conn = getConnection();
            String sql = "DELETE FROM purchase_order_details WHERE pod_id = ?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, podId);
            
            int result = stmt.executeUpdate();
            stmt.close();
            
            return result > 0;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
