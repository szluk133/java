package service;

import dao.MenuItemDAO;
import model.MenuItem;

import java.math.BigDecimal;
import java.util.List;

public class MenuItemService {
    private final MenuItemDAO menuItemDAO;

    public MenuItemService() {
        this.menuItemDAO = new MenuItemDAO();
    }

    /**
     * Lấy tất cả mặt hàng trong menu
     */
    public List<MenuItem> getAllItems() {
        return menuItemDAO.getAllItems();
    }

    /**
     * Lấy mặt hàng theo ID
     */
    public MenuItem getItemById(int itemId) {
        return menuItemDAO.getItemById(itemId);
    }

    /**
     * Thêm mặt hàng mới
     * @return true nếu thêm thành công, false nếu thất bại
     */
    public boolean addMenuItem(String name, BigDecimal unitPrice, BigDecimal costPrice, String category, String status) {
        // Validate input
        if (name == null || name.trim().isEmpty()) {
            System.err.println("Tên mặt hàng không được để trống");
            return false;
        }

        if (unitPrice == null || unitPrice.compareTo(BigDecimal.ZERO) <= 0) {
            System.err.println("Giá bán phải lớn hơn 0");
            return false;
        }

        if (costPrice != null && costPrice.compareTo(BigDecimal.ZERO) < 0) {
            System.err.println("Giá nhập không thể là số âm");
            return false;
        }

        // Đảm bảo trạng thái hợp lệ (active/inactive)
        if (status == null || status.trim().isEmpty()) {
            status = "active"; // Mặc định là active
        } else if (!status.equals("active") && !status.equals("inactive")) {
            System.err.println("Trạng thái không hợp lệ. Sử dụng 'active' hoặc 'inactive'");
            return false;
        }

        MenuItem newItem = new MenuItem(0, name, unitPrice, costPrice, category, status);
        return menuItemDAO.addMenuItem(newItem);
    }

    /**
     * Cập nhật thông tin mặt hàng
     * @return true nếu cập nhật thành công, false nếu thất bại
     */
    public boolean updateMenuItem(int itemId, String name, BigDecimal unitPrice, BigDecimal costPrice, String category, String status) {
        // Kiểm tra xem mặt hàng có tồn tại không
        MenuItem existingItem = menuItemDAO.getItemById(itemId);
        if (existingItem == null) {
            System.err.println("Không tìm thấy mặt hàng với ID: " + itemId);
            return false;
        }

        // Validate input
        if (name == null || name.trim().isEmpty()) {
            System.err.println("Tên mặt hàng không được để trống");
            return false;
        }

        if (unitPrice == null || unitPrice.compareTo(BigDecimal.ZERO) <= 0) {
            System.err.println("Giá bán phải lớn hơn 0");
            return false;
        }

        if (costPrice != null && costPrice.compareTo(BigDecimal.ZERO) < 0) {
            System.err.println("Giá nhập không thể là số âm");
            return false;
        }

        // Đảm bảo trạng thái hợp lệ (active/inactive)
        if (status == null || status.trim().isEmpty()) {
            status = existingItem.getStatus(); // Giữ nguyên status cũ nếu không cung cấp
        } else if (!status.equals("active") && !status.equals("inactive")) {
            System.err.println("Trạng thái không hợp lệ. Sử dụng 'active' hoặc 'inactive'");
            return false;
        }

        // Cập nhật thông tin
        existingItem.setName(name);
        existingItem.setUnitPrice(unitPrice);
        existingItem.setCostPrice(costPrice);
        existingItem.setCategory(category);
        existingItem.setStatus(status);

        return menuItemDAO.updateMenuItem(existingItem);
    }

    /**
     * Xóa mặt hàng
     * @return true nếu xóa thành công, false nếu thất bại
     */
    public boolean deleteMenuItem(int itemId) {
        // Kiểm tra xem mặt hàng có tồn tại không
        MenuItem existingItem = menuItemDAO.getItemById(itemId);
        if (existingItem == null) {
            System.err.println("Không tìm thấy mặt hàng với ID: " + itemId);
            return false;
        }

        return menuItemDAO.deleteMenuItem(itemId);
    }
} 