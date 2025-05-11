package ui;

import dao.InventoryDAO;
import dao.MenuItemDAO;
import model.Inventory;
import model.MenuItem;

import java.util.List;
import java.util.Scanner;

public class InventoryUI {
    private Scanner scanner;
    private InventoryDAO inventoryDAO;
    private MenuItemDAO menuItemDAO;

    public InventoryUI() {
        scanner = new Scanner(System.in);
        inventoryDAO = new InventoryDAO();
        menuItemDAO = new MenuItemDAO();
    }

    public void displayMenu() {
        int choice;
        do {
            System.out.println("\n====================== QUẢN LÝ TỒN KHO ======================");
            System.out.println("1. Xem danh sách tồn kho");
            System.out.println("2. Tìm kiếm tồn kho theo mã sản phẩm");
            System.out.println("3. Cập nhật ngưỡng đặt hàng lại");
            System.out.println("4. Nhập thêm hàng vào kho");
            System.out.println("0. Quay lại");
            System.out.print("Chọn chức năng: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại!");
                choice = -1;
                continue;
            }

            switch (choice) {
                case 1:
                    displayAllInventories();
                    break;
                case 2:
                    searchInventoryByItemId();
                    break;
                case 3:
                    updateReorderLevel();
                    break;
                case 4:
                    addToInventory();
                    break;
                case 0:
                    System.out.println("Quay lại menu trước...");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại!");
            }
        } while (choice != 0);
    }
    
    private void displayAllInventories() {
        System.out.println("\n========================= DANH SÁCH TỒN KHO =========================");
        List<Inventory> inventories = inventoryDAO.getAllInventories();
        
        if (inventories.isEmpty()) {
            System.out.println("Không có dữ liệu tồn kho nào trong hệ thống!");
            return;
        }

        // In tiêu đề bảng
        displayInventoryTableHeader();
        
        // In dữ liệu
        int stt = 1;
        for (Inventory inventory : inventories) {
            displayInventoryRow(stt++, inventory);
        }
        
        System.out.println("==========================================================================");
        
        System.out.print("\nNhấn Enter để quay lại...");
        scanner.nextLine();
    }
    
    private void searchInventoryByItemId() {
        System.out.print("\nNhập mã sản phẩm: ");
        try {
            int itemId = Integer.parseInt(scanner.nextLine());
            
            Inventory inventory = inventoryDAO.getInventoryByItemId(itemId);
            if (inventory != null) {
                System.out.println("\n========================= THÔNG TIN TỒN KHO =========================");
                displayInventoryTableHeader();
                displayInventoryRow(1, inventory);
                System.out.println("==========================================================================");
            } else {
                System.out.println("Không tìm thấy thông tin tồn kho cho mã sản phẩm: " + itemId);
            }
        } catch (NumberFormatException e) {
            System.out.println("Mã sản phẩm không hợp lệ!");
        }
        
        System.out.print("\nNhấn Enter để quay lại...");
        scanner.nextLine();
    }
    
    private void updateReorderLevel() {
        System.out.print("\nNhập mã sản phẩm cần cập nhật ngưỡng đặt hàng lại: ");
        try {
            int itemId = Integer.parseInt(scanner.nextLine());
            
            // Kiểm tra tồn tại
            Inventory inventory = inventoryDAO.getInventoryByItemId(itemId);
            if (inventory == null) {
                System.out.println("Không tìm thấy thông tin tồn kho cho mã sản phẩm: " + itemId);
                return;
            }
            
            System.out.print("Nhập ngưỡng đặt hàng lại mới: ");
            int newLevel = Integer.parseInt(scanner.nextLine());
            
            if (newLevel < 0) {
                System.out.println("Ngưỡng đặt hàng lại không thể là số âm!");
                return;
            }
            
            inventoryDAO.updateReorderLevel(itemId, newLevel);
            System.out.println("Cập nhật thành công!");
        } catch (NumberFormatException e) {
            System.out.println("Giá trị nhập không hợp lệ!");
        }
        
        System.out.print("\nNhấn Enter để quay lại...");
        scanner.nextLine();
    }
    
    private void addToInventory() {
        System.out.println("\n=================== NHẬP THÊM HÀNG VÀO KHO ===================");
        
        // Hiển thị danh sách các sản phẩm
        List<MenuItem> menuItems = menuItemDAO.getAllItems();
        if (menuItems.isEmpty()) {
            System.out.println("Không có sản phẩm nào trong hệ thống!");
            return;
        }
        
        System.out.println("Danh sách sản phẩm:");
        System.out.printf("%-5s | %-30s | %-12s | %-15s | %-10s%n",
                "ID", "Tên sản phẩm", "Giá", "Danh mục", "Trạng thái");
        System.out.println("------------------------------------------------------------------------");
        
        for (MenuItem item : menuItems) {
            if ("active".equals(item.getStatus())) {
                System.out.printf("%-5d | %-30s | %-12s | %-15s | %-10s%n",
                        item.getItemId(),
                        item.getName(),
                        item.getUnitPrice(),
                        item.getCategory(),
                        item.getStatus());
            }
        }
        
        try {
            System.out.print("\nNhập mã sản phẩm cần nhập kho (0 để hủy): ");
            int itemId = Integer.parseInt(scanner.nextLine());
            
            if (itemId == 0) {
                System.out.println("Hủy nhập kho.");
                return;
            }
            
            // Kiểm tra sản phẩm tồn tại và còn hoạt động
            MenuItem selectedItem = menuItemDAO.getItemById(itemId);
            if (selectedItem == null || !"active".equals(selectedItem.getStatus())) {
                System.out.println("Sản phẩm không tồn tại hoặc không còn hoạt động!");
                return;
            }
            
            System.out.println("Sản phẩm: " + selectedItem.getName());
            
            // Lấy thông tin tồn kho hiện tại
            Inventory inventory = inventoryDAO.getInventoryByItemId(itemId);
            if (inventory != null) {
                System.out.println("Tồn kho hiện tại: " + inventory.getStockOnHand());
                System.out.println("Tổng số lượng đã nhập: " + inventory.getTotalQuantity());
                System.out.println("Số lượng đã bán: " + inventory.getSoldQuantity());
            } else {
                System.out.println("Sản phẩm chưa có trong kho.");
            }
            
            System.out.print("Nhập số lượng cần thêm vào kho: ");
            int quantity = Integer.parseInt(scanner.nextLine());
            
            if (quantity <= 0) {
                System.out.println("Số lượng phải lớn hơn 0!");
                return;
            }
            
            boolean success = inventoryDAO.addToInventory(itemId, quantity);
            if (success) {
                System.out.println("Đã nhập thêm " + quantity + " " + selectedItem.getName() + " vào kho thành công!");
            } else {
                System.out.println("Có lỗi xảy ra khi nhập kho!");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Giá trị nhập không hợp lệ!");
        }
        
        System.out.print("\nNhấn Enter để quay lại...");
        scanner.nextLine();
    }
    
    private void displayInventoryTableHeader() {
        System.out.println("---------------------------------------------------------------------------");
        System.out.printf("| %-4s | %-6s | %-25s | %-10s | %-10s | %-10s |\n", 
                         "STT", "Mã SP", "Tên sản phẩm", "Đã bán", "Tổng cộng", "Tồn kho");
        System.out.println("---------------------------------------------------------------------------");
    }
    
    private void displayInventoryRow(int stt, Inventory inventory) {
        // Cắt tên sản phẩm nếu quá dài
        String itemName = inventory.getItemName();
        if (itemName == null) {
            // Nếu không có tên trong Inventory, thử lấy từ MenuItemDAO
            MenuItem item = menuItemDAO.getItemById(inventory.getItemId());
            itemName = (item != null) ? item.getName() : "Không xác định";
        }
        
        if (itemName != null && itemName.length() > 25) {
            itemName = itemName.substring(0, 22) + "...";
        }
        
        System.out.printf("| %-4d | %-6d | %-25s | %-10d | %-10d | %-10d |\n",
                         stt,
                         inventory.getItemId(),
                         itemName,
                         inventory.getSoldQuantity(),
                         inventory.getTotalQuantity(),
                         inventory.getStockOnHand());
    }
}
