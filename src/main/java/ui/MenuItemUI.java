package ui;

import model.MenuItem;
import service.MenuItemService;
import dao.InventoryDAO;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class MenuItemUI {
    private final MenuItemService menuItemService;
    private final InventoryDAO inventoryDAO;
    private final Scanner scanner;
    private final NumberFormat currencyFormatter;

    public MenuItemUI() {
        this.menuItemService = new MenuItemService();
        this.inventoryDAO = new InventoryDAO();
        this.scanner = new Scanner(System.in);
        this.currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
    }

    public void menu() {
        int choice;
        do {
            System.out.println("\n=== QUẢN LÝ MENU BÁN HÀNG ===");
            System.out.println("1. Xem danh sách menu");
            System.out.println("2. Thêm mặt hàng mới");
            System.out.println("3. Sửa thông tin mặt hàng");
            System.out.println("4. Xóa mặt hàng");
            System.out.println("0. Quay lại");
            System.out.print("Chọn chức năng: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        viewAllMenuItems();
                        break;
                    case 2:
                        addMenuItem();
                        break;
                    case 3:
                        updateMenuItem();
                        break;
                    case 4:
                        deleteMenuItem();
                        break;
                    case 0:
                        System.out.println("Quay lại menu chính...");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số.");
                choice = -1;
            }
        } while (choice != 0);
    }

    private void viewAllMenuItems() {
        List<MenuItem> items = menuItemService.getAllItems();

        if (items.isEmpty()) {
            System.out.println("Không có mặt hàng nào trong menu.");
            return;
        }

        System.out.println("\n====== DANH SÁCH MẶT HÀNG ======");
        System.out.printf("%-5s | %-30s | %-15s | %-15s | %-15s | %-10s\n",
                "ID", "Tên mặt hàng", "Giá bán", "Giá nhập", "Danh mục", "Trạng thái");
        System.out.println("--------------------------------------------------------------------------------------------");

        for (MenuItem item : items) {
            System.out.printf("%-5d | %-30s | %-15s | %-15s | %-15s | %-10s\n",
                    item.getItemId(),
                    item.getName(),
                    formatCurrency(item.getUnitPrice()),
                    formatCurrency(item.getCostPrice()),
                    item.getCategory() != null ? item.getCategory() : "N/A",
                    item.getStatus());
        }

        System.out.println("--------------------------------------------------------------------------------------------");
    }

    private void addMenuItem() {
        System.out.println("\n=== THÊM MẶT HÀNG MỚI ===");
        
        System.out.print("Tên mặt hàng: ");
        String name = scanner.nextLine();

        BigDecimal unitPrice = readBigDecimal("Giá bán: ");
        
        BigDecimal costPrice = null;
        System.out.print("Giá nhập (nhấn Enter để bỏ qua): ");
        String costPriceStr = scanner.nextLine();
        if (!costPriceStr.isEmpty()) {
            try {
                costPrice = new BigDecimal(costPriceStr);
            } catch (NumberFormatException e) {
                System.out.println("Giá nhập không hợp lệ, sẽ được đặt là null.");
            }
        }

        System.out.print("Danh mục (nhấn Enter để bỏ qua): ");
        String category = scanner.nextLine();

        System.out.print("Trạng thái (active/inactive, mặc định là active): ");
        String status = scanner.nextLine();
        if (status.isEmpty()) {
            status = "active";
        }
        
        int initialQuantity = 0;
        System.out.print("Số lượng nhập kho ban đầu (nhấn Enter để bỏ qua): ");
        String quantityStr = scanner.nextLine();
        if (!quantityStr.isEmpty()) {
            try {
                initialQuantity = Integer.parseInt(quantityStr);
                if (initialQuantity < 0) {
                    System.out.println("Số lượng không được âm, sẽ được đặt là 0.");
                    initialQuantity = 0;
                }
            } catch (NumberFormatException e) {
                System.out.println("Số lượng không hợp lệ, sẽ được đặt là 0.");
            }
        }

        boolean success = menuItemService.addMenuItem(name, unitPrice, costPrice, category, status);
        if (success) {
            System.out.println("Thêm mặt hàng thành công!");
            
            List<MenuItem> items = menuItemService.getAllItems();
            MenuItem newItem = null;
            for (MenuItem item : items) {
                if (item.getName().equals(name) && item.getUnitPrice().equals(unitPrice)) {
                    newItem = item;
                    break;
                }
            }
            
            if (newItem != null && initialQuantity > 0) {
                if (inventoryDAO.addToInventory(newItem.getItemId(), initialQuantity)) {
                    System.out.println("Đã thêm " + initialQuantity + " đơn vị vào kho.");
                } else {
                    System.out.println("Thêm mặt hàng thành công, nhưng không thể khởi tạo tồn kho.");
                }
            }
        } else {
            System.out.println("Thêm mặt hàng thất bại.");
        }
    }

    private void updateMenuItem() {
        System.out.println("\n=== CẬP NHẬT THÔNG TIN MẶT HÀNG ===");
        
        System.out.print("Nhập ID mặt hàng cần cập nhật: ");
        int itemId;
        try {
            itemId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID không hợp lệ.");
            return;
        }

        MenuItem item = menuItemService.getItemById(itemId);
        if (item == null) {
            System.out.println("Không tìm thấy mặt hàng với ID: " + itemId);
            return;
        }

        System.out.println("Thông tin hiện tại:");
        System.out.println("Tên: " + item.getName());
        System.out.println("Giá bán: " + formatCurrency(item.getUnitPrice()));
        System.out.println("Giá nhập: " + formatCurrency(item.getCostPrice()));
        System.out.println("Danh mục: " + (item.getCategory() != null ? item.getCategory() : "N/A"));
        System.out.println("Trạng thái: " + item.getStatus());
        System.out.println("\nNhập thông tin mới (nhấn Enter để giữ nguyên giá trị cũ):");

        System.out.print("Tên mặt hàng: ");
        String name = scanner.nextLine();
        if (name.isEmpty()) name = item.getName();

        BigDecimal unitPrice = item.getUnitPrice();
        System.out.print("Giá bán: ");
        String unitPriceStr = scanner.nextLine();
        if (!unitPriceStr.isEmpty()) {
            try {
                unitPrice = new BigDecimal(unitPriceStr);
            } catch (NumberFormatException e) {
                System.out.println("Giá bán không hợp lệ, giữ nguyên giá trị cũ.");
            }
        }

        BigDecimal costPrice = item.getCostPrice();
        System.out.print("Giá nhập: ");
        String costPriceStr = scanner.nextLine();
        if (!costPriceStr.isEmpty()) {
            try {
                costPrice = new BigDecimal(costPriceStr);
            } catch (NumberFormatException e) {
                System.out.println("Giá nhập không hợp lệ, giữ nguyên giá trị cũ.");
            }
        }

        System.out.print("Danh mục: ");
        String category = scanner.nextLine();
        if (category.isEmpty()) category = item.getCategory();

        System.out.print("Trạng thái (active/inactive): ");
        String status = scanner.nextLine();
        if (status.isEmpty()) status = item.getStatus();

        boolean success = menuItemService.updateMenuItem(itemId, name, unitPrice, costPrice, category, status);
        if (success) {
            System.out.println("Cập nhật mặt hàng thành công!");
        } else {
            System.out.println("Cập nhật mặt hàng thất bại.");
        }
    }

    private void deleteMenuItem() {
        System.out.println("\n=== XÓA MẶT HÀNG ===");
        
        System.out.print("Nhập ID mặt hàng cần xóa: ");
        int itemId;
        try {
            itemId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID không hợp lệ.");
            return;
        }

        MenuItem item = menuItemService.getItemById(itemId);
        if (item == null) {
            System.out.println("Không tìm thấy mặt hàng với ID: " + itemId);
            return;
        }

        System.out.println("Bạn có chắc chắn muốn xóa mặt hàng sau?");
        System.out.println("ID: " + item.getItemId());
        System.out.println("Tên: " + item.getName());
        System.out.println("Giá bán: " + formatCurrency(item.getUnitPrice()));
        System.out.print("Xác nhận xóa (Y/N): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("Y")) {
            boolean success = menuItemService.deleteMenuItem(itemId);
            if (success) {
                System.out.println("Xóa mặt hàng thành công!");
            } else {
                System.out.println("Xóa mặt hàng thất bại.");
            }
        } else {
            System.out.println("Đã hủy thao tác xóa.");
        }
    }

    private BigDecimal readBigDecimal(String prompt) {
        BigDecimal value = null;
        boolean valid = false;
        
        while (!valid) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            
            try {
                value = new BigDecimal(input);
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Giá trị không hợp lệ. Vui lòng nhập lại.");
            }
        }
        
        return value;
    }

    private String formatCurrency(BigDecimal amount) {
        if (amount == null) {
            return "N/A";
        }
        return currencyFormatter.format(amount);
    }
} 