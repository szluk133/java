package dao;

import model.Employee;
import model.Supplier;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {
    private Connection conn = DatabaseConnection.getConnection();
    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM suppliers";

        try (
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Supplier s = new Supplier(
                        rs.getInt("supplier_id"),
                        rs.getString("name"),
                        rs.getString("contact_person"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("email")
                );
                suppliers.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return suppliers;
    }

    public void addSupplier(Supplier supplier) {
        String sql = "INSERT INTO suppliers (name, contact_person, phone, address, email) VALUES (?, ?, ?, ?, ?)";

        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getContactPerson());
            stmt.setString(3, supplier.getPhone());
            stmt.setString(4, supplier.getAddress());
            stmt.setString(5, supplier.getEmail());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateSupplier(Supplier supplier) {
        String sql = "UPDATE suppliers SET name=?, contact_person=?, phone=?, address=?, email=? WHERE supplier_id=?";

        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getContactPerson());
            stmt.setString(3, supplier.getPhone());
            stmt.setString(4, supplier.getAddress());
            stmt.setString(5, supplier.getEmail());
            stmt.setInt(6, supplier.getSupplierId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSupplier(int id) {
        String sql = "DELETE FROM suppliers WHERE supplier_id = ?";

        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Supplier getSupplierById(int id) throws SQLException {
        String sql = "SELECT * FROM suppliers WHERE supplier_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Supplier sup = new Supplier();
                sup.setSupplierId(rs.getInt("supplier_id"));
                sup.setName(rs.getString("name"));
                sup.setContactPerson(rs.getString("contact_person"));
                sup.setPhone(rs.getString("phone"));
                sup.setAddress(rs.getString("address"));
                sup.setEmail(rs.getString("email"));
                return sup;
            }
        }
        return null;
    }
}
