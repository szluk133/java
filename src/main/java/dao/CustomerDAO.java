package dao;

import model.Customer;

import java.sql.*;
import java.util.*;

public class CustomerDAO {
    private Connection conn = DatabaseConnection.getConnection();

    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Customer c = new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getTimestamp("registered_at")
                );
                customers.add(c);
            }
        } catch (SQLException e) {
            throw e;
        }
        return customers;
    }

    public void addCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO customers (name, address, phone, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getAddress());
            stmt.setString(3, customer.getPhone());
            stmt.setString(4, customer.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    /**
     * Thêm khách hàng mới và trả về ID của khách hàng đó
     * @param customer Thông tin khách hàng cần thêm
     * @return ID của khách hàng mới thêm, hoặc -1 nếu thất bại
     */
    public int addCustomerWithReturn(Customer customer) throws SQLException {
        String sql = "INSERT INTO customers (name, address, phone, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getAddress());
            stmt.setString(3, customer.getPhone());
            stmt.setString(4, customer.getEmail());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                return -1;
            }
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    return -1;
                }
            }
        }
    }

    /**
     * Kiểm tra xem email đã tồn tại trong hệ thống chưa
     * @param email Email cần kiểm tra
     * @return true nếu email đã tồn tại, false nếu chưa
     */
    public boolean isEmailExists(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM customers WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    /**
     * Kiểm tra xem số điện thoại đã tồn tại trong hệ thống chưa
     * @param phone Số điện thoại cần kiểm tra
     * @return true nếu số điện thoại đã tồn tại, false nếu chưa
     */
    public boolean isPhoneExists(String phone) throws SQLException {
        String sql = "SELECT COUNT(*) FROM customers WHERE phone = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, phone);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    public Customer getCustomerById(int id) throws SQLException {
        String sql = "SELECT * FROM customers WHERE customer_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(
                    rs.getInt("customer_id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("phone"),
                    rs.getString("email"),
                    rs.getTimestamp("registered_at")
                );
            }
        } catch (SQLException e) {
            throw e;
        }
        return null;
    }

    public void updateCustomer(Customer customer) throws SQLException {
        String sql = "UPDATE customers SET name=?, address=?, phone=?, email=? WHERE customer_id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getAddress());
            stmt.setString(3, customer.getPhone());
            stmt.setString(4, customer.getEmail());
            stmt.setInt(5, customer.getCustomerId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void deleteCustomer(int id) throws SQLException {
        String sql = "DELETE FROM customers WHERE customer_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
}
