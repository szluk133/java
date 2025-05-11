package dao;

import model.MenuItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuItemDAO {
    private Connection conn = DatabaseConnection.getConnection();

    /**
     * Lấy tất cả món ăn từ menu
     */
    public List<MenuItem> getAllItems() {
        List<MenuItem> items = new ArrayList<>();
        String sql = "SELECT * FROM menu_items";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                MenuItem item = extractMenuItemFromResultSet(rs);
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return items;
    }
    
    /**
     * Lấy thông tin món ăn theo ID
     */
    public MenuItem getItemById(int itemId) {
        String sql = "SELECT * FROM menu_items WHERE item_id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, itemId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractMenuItemFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    /**
     * Alias for getItemById, to match the method name used in CustomerInvoiceUI
     */
    public MenuItem getMenuItemById(int itemId) {
        return getItemById(itemId);
    }
    
    /**
     * Helper method để chuyển đổi ResultSet thành đối tượng MenuItem
     */
    private MenuItem extractMenuItemFromResultSet(ResultSet rs) throws SQLException {
        return new MenuItem(
            rs.getInt("item_id"),
            rs.getString("name"),
            rs.getBigDecimal("unit_price"),
            rs.getBigDecimal("cost_price"),
            rs.getString("category"),
            rs.getString("status")
        );
    }
    
    /**
     * Tìm kiếm món ăn theo tên
     */
    public List<MenuItem> searchItemsByName(String keyword) {
        List<MenuItem> items = new ArrayList<>();
        String sql = "SELECT * FROM menu_items WHERE name LIKE ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    MenuItem item = extractMenuItemFromResultSet(rs);
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return items;
    }

    /**
     * Thêm mặt hàng mới vào menu
     */
    public boolean addMenuItem(MenuItem item) {
        String sql = "INSERT INTO menu_items (name, unit_price, cost_price, category, status) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, item.getName());
            pstmt.setBigDecimal(2, item.getUnitPrice());
            
            // cost_price có thể null
            if (item.getCostPrice() != null) {
                pstmt.setBigDecimal(3, item.getCostPrice());
            } else {
                pstmt.setNull(3, java.sql.Types.DECIMAL);
            }
            
            // category có thể null
            if (item.getCategory() != null && !item.getCategory().trim().isEmpty()) {
                pstmt.setString(4, item.getCategory());
            } else {
                pstmt.setNull(4, java.sql.Types.VARCHAR);
            }
            
            pstmt.setString(5, item.getStatus());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        item.setItemId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Cập nhật thông tin mặt hàng
     */
    public boolean updateMenuItem(MenuItem item) {
        String sql = "UPDATE menu_items SET name = ?, unit_price = ?, cost_price = ?, category = ?, status = ? WHERE item_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, item.getName());
            pstmt.setBigDecimal(2, item.getUnitPrice());
            
            // cost_price có thể null
            if (item.getCostPrice() != null) {
                pstmt.setBigDecimal(3, item.getCostPrice());
            } else {
                pstmt.setNull(3, java.sql.Types.DECIMAL);
            }
            
            // category có thể null
            if (item.getCategory() != null && !item.getCategory().trim().isEmpty()) {
                pstmt.setString(4, item.getCategory());
            } else {
                pstmt.setNull(4, java.sql.Types.VARCHAR);
            }
            
            pstmt.setString(5, item.getStatus());
            pstmt.setInt(6, item.getItemId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Xóa mặt hàng khỏi menu
     */
    public boolean deleteMenuItem(int itemId) {
        String sql = "DELETE FROM menu_items WHERE item_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, itemId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
} 