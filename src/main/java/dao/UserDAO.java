package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private DatabaseConnection dbConnection = new DatabaseConnection();

    public User findUserByUsername(String username) {
        User user = null;
        String sql = "SELECT user_id, username, password_hash, role_id, employee_id, customer_id, created_at " +
                     "FROM users WHERE username = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                user = new User(
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    rs.getString("password_hash"),
                    rs.getInt("role_id"),
                    (rs.getObject("employee_id") != null) ? rs.getInt("employee_id") : null,
                    (rs.getObject("customer_id") != null) ? rs.getInt("customer_id") : null,
                    rs.getTimestamp("created_at")
                );
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm người dùng: " + e.getMessage());
        }
        
        return user;
    }
    
    /**
     * Kiểm tra xem username đã tồn tại trong hệ thống chưa
     * @param username Tên đăng nhập cần kiểm tra
     * @return true nếu username đã tồn tại, false nếu chưa
     */
    public boolean checkUsernameExists(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi kiểm tra tên người dùng: " + e.getMessage());
        }
        
        return false;
    }
    
    /**
     * Thêm một user mới vào hệ thống
     * @param user Đối tượng User cần thêm
     * @return true nếu thêm thành công, false nếu thất bại
     */
    public boolean addUser(User user) {
        String sql = "INSERT INTO users (username, password_hash, role_id, employee_id, customer_id) " +
                     "VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPasswordHash());
            stmt.setInt(3, user.getRoleId());
            
            if (user.getEmployeeId() != null) {
                stmt.setInt(4, user.getEmployeeId());
            } else {
                stmt.setNull(4, java.sql.Types.INTEGER);
            }
            
            if (user.getCustomerId() != null) {
                stmt.setInt(5, user.getCustomerId());
            } else {
                stmt.setNull(5, java.sql.Types.INTEGER);
            }
            
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm người dùng: " + e.getMessage());
            return false;
        }
    }
}
