package dao;
import model.Inventory;
import model.MenuItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO {
    private Connection conn = DatabaseConnection.getConnection();
    private MenuItemDAO menuItemDAO = new MenuItemDAO();

    public List<Inventory> getAllInventories() {
        List<Inventory> inventories = new ArrayList<>();
        String sql = "SELECT * FROM inventories";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Inventory inventory = new Inventory(
                        rs.getInt("item_id"),
                        rs.getInt("reorder_level"),
                        rs.getInt("sold_quantity"),
                        rs.getInt("total_quantity"),
                        rs.getInt("stock_on_hand")
                );
                
                MenuItem menuItem = menuItemDAO.getItemById(inventory.getItemId());
                if (menuItem != null) {
                    inventory.setItemName(menuItem.getName());
                }
                
                inventories.add(inventory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inventories;
    }

    public Inventory getInventoryByItemId(int itemId) {
        String sql = "SELECT * FROM inventories WHERE item_id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, itemId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Inventory inventory = new Inventory(
                            rs.getInt("item_id"),
                            rs.getInt("reorder_level"),
                            rs.getInt("sold_quantity"),
                            rs.getInt("total_quantity"),
                            rs.getInt("stock_on_hand")
                    );
                    
                    MenuItem menuItem = menuItemDAO.getItemById(inventory.getItemId());
                    if (menuItem != null) {
                        inventory.setItemName(menuItem.getName());
                    }
                    
                    return inventory;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    public void updateReorderLevel(int itemId, int newLevel) {
        String sql = "UPDATE inventories SET reorder_level = ? WHERE item_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, newLevel);
            stmt.setInt(2, itemId);
            stmt.executeUpdate();
            System.out.println("Ngưỡng đặt hàng đã được cập nhật thành công.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean updateInventoryAfterSale(int itemId, int quantitySold) {
        String sql = "UPDATE inventories SET sold_quantity = sold_quantity + ? WHERE item_id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantitySold);
            stmt.setInt(2, itemId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean addToInventory(int itemId, int quantity) {
        // Kiểm tra xem sản phẩm đã có trong bảng inventory chưa
        String checkSql = "SELECT * FROM inventories WHERE item_id = ?";
        
        try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setInt(1, itemId);
            
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    // Sản phẩm đã tồn tại, cập nhật total_quantity
                    String updateSql = "UPDATE inventories SET total_quantity = total_quantity + ? WHERE item_id = ?";
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                        updateStmt.setInt(1, quantity);
                        updateStmt.setInt(2, itemId);
                        
                        int rowsAffected = updateStmt.executeUpdate();
                        return rowsAffected > 0;
                    }
                } else {
                    // Sản phẩm chưa tồn tại, thêm mới vào inventories
                    String insertSql = "INSERT INTO inventories (item_id, reorder_level, sold_quantity, total_quantity) VALUES (?, 10, 0, ?)";
                    try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                        insertStmt.setInt(1, itemId);
                        insertStmt.setInt(2, quantity);
                        
                        int rowsAffected = insertStmt.executeUpdate();
                        return rowsAffected > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public int getStockQuantity(int itemId) {
        String sql = "SELECT stock_on_hand FROM inventories WHERE item_id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, itemId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("stock_on_hand");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return 0;
    }
}
