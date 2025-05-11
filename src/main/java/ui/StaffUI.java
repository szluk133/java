package ui;

import dao.CustomerDAO;
import dao.MenuItemDAO;
import dao.InvoiceDAO;
import dao.InvoiceDetailDAO;
import dao.InventoryDAO;
import model.Customer;
import model.MenuItem;
import model.Invoice;
import model.InvoiceDetail;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class StaffUI {
    private final Scanner scanner;
    private final int employeeId;
    private final CustomerDAO customerDAO;
    private final MenuItemDAO menuItemDAO;
    private final InvoiceDAO invoiceDAO;
    private final InvoiceDetailDAO invoiceDetailDAO;
    private final InventoryDAO inventoryDAO;

    public StaffUI(int employeeId) {
        this.scanner = new Scanner(System.in);
        this.employeeId = employeeId;
        this.customerDAO = new CustomerDAO();
        this.menuItemDAO = new MenuItemDAO();
        this.invoiceDAO = new InvoiceDAO();
        this.invoiceDetailDAO = new InvoiceDetailDAO();
        this.inventoryDAO = new InventoryDAO();
    }

    public void displayStaffMenu() {
        int choice;
        do {
            System.out.println("\n================= GIAO DIỆN NHÂN VIÊN =================");
            System.out.println("1. Tạo hóa đơn mới");
            System.out.println("2. Xem danh sách hóa đơn");
            System.out.println("3. Xem danh sách sản phẩm");
            System.out.println("4. Quản lý tồn kho");
            System.out.println("0. Đăng xuất");
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
                    createNewInvoice();
                    break;
                case 2:
                    viewInvoices();
                    break;
                case 3:
                    viewMenuItems();
                    break;
                case 4:
                    manageInventory();
                    break;
                case 0:
                    System.out.println("Đăng xuất thành công!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại!");
            }
        } while (choice != 0);
    }

    private void createNewInvoice() {
        System.out.println("\n================= TẠO HÓA ĐƠN MỚI =================");
        
        // Bước 1: Chọn khách hàng
        int customerId = selectCustomer();
        if (customerId == -1) {
            System.out.println("Hủy tạo hóa đơn.");
            return;
        }
        
        // Bước 2: Thêm sản phẩm vào hóa đơn
        List<InvoiceDetail> invoiceDetails = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;
        
        boolean addingItems = true;
        while (addingItems) {
            InvoiceDetail detail = addItemToInvoice();
            if (detail != null) {
                invoiceDetails.add(detail);
                totalAmount = totalAmount.add(detail.getLineTotal());
                
                System.out.print("Tiếp tục thêm sản phẩm? (y/n): ");
                String continueAdding = scanner.nextLine();
                if (!continueAdding.equalsIgnoreCase("y")) {
                    addingItems = false;
                }
            } else {
                System.out.print("Hủy thêm sản phẩm này? Tiếp tục thêm sản phẩm khác? (y/n): ");
                String continueAdding = scanner.nextLine();
                if (!continueAdding.equalsIgnoreCase("y")) {
                    addingItems = false;
                }
            }
        }
        
        if (invoiceDetails.isEmpty()) {
            System.out.println("Không có sản phẩm nào được thêm. Hủy tạo hóa đơn.");
            return;
        }
        
        // Bước 3: Tính toán và hiển thị tổng tiền
        System.out.println("\nTổng tiền: " + totalAmount);
        
        // Thuế (0.2% tổng tiền)
        BigDecimal tax = totalAmount.multiply(new BigDecimal("0.002"));
        System.out.println("Thuế (0.2%): " + tax);
        
        // Tổng cộng
        BigDecimal totalWithTax = totalAmount.add(tax);
        System.out.println("Tổng cộng: " + totalWithTax);
        
        // Bước 4: Xác nhận tạo hóa đơn
        System.out.print("\nXác nhận tạo hóa đơn? (y/n): ");
        String confirm = scanner.nextLine();
        
        if (confirm.equalsIgnoreCase("y")) {
            // Tạo hóa đơn - chỉ truyền những thông tin cần thiết, không truyền các trường được tính tự động
            Invoice newInvoice = new Invoice();
            newInvoice.setCustomerId(customerId);
            newInvoice.setEmployeeId(employeeId);
            newInvoice.setInvoiceDate(new Timestamp(new Date().getTime()));
            newInvoice.setTotalAmount(totalAmount);
            newInvoice.setStatus("paid");
            
            int invoiceId = invoiceDAO.addInvoice(newInvoice);
            
            if (invoiceId > 0) {
                boolean allSuccess = true;
                
                // Thêm chi tiết hóa đơn
                for (InvoiceDetail detail : invoiceDetails) {
                    detail.setInvoiceId(invoiceId);
                    int detailId = invoiceDetailDAO.addInvoiceDetail(detail);
                    
                    if (detailId <= 0) {
                        allSuccess = false;
                        System.out.println("Lỗi khi thêm chi tiết hóa đơn cho sản phẩm ID: " + detail.getItemId());
                    } else {
                        // Cập nhật tồn kho - giảm số lượng sản phẩm
                        boolean updated = inventoryDAO.updateInventoryAfterSale(detail.getItemId(), detail.getQuantity());
                        if (!updated) {
                            System.out.println("Cảnh báo: Không thể cập nhật tồn kho cho sản phẩm ID: " + detail.getItemId());
                        }
                    }
                }
                
                // Lấy thông tin hóa đơn sau khi tạo thành công (bao gồm tax và discount đã được tính tự động)
                Invoice createdInvoice = invoiceDAO.getInvoiceById(invoiceId);
                if (createdInvoice != null) {
                    System.out.println("\n================= THÔNG TIN HÓA ĐƠN =================");
                    System.out.println("Mã hóa đơn: " + createdInvoice.getInvoiceId());
                    System.out.println("Tổng tiền: " + createdInvoice.getTotalAmount());
                    System.out.println("Thuế: " + createdInvoice.getTax());
                    System.out.println("Tổng cộng: " + createdInvoice.getDiscount());
                    System.out.println("Trạng thái: " + createdInvoice.getStatus());
                }
                
                if (allSuccess) {
                    System.out.println("\nTạo hóa đơn thành công! Mã hóa đơn: " + invoiceId);
                } else {
                    System.out.println("\nĐã xảy ra lỗi khi thêm chi tiết hóa đơn, vui lòng kiểm tra lại!");
                }
            } else {
                System.out.println("Lỗi khi tạo hóa đơn, vui lòng thử lại!");
            }
        } else {
            System.out.println("Hủy tạo hóa đơn.");
        }
    }
    
    private int selectCustomer() {
        System.out.println("\n--- Chọn khách hàng ---");
        
        try {
            List<Customer> customers = customerDAO.getAllCustomers();
            if (customers.isEmpty()) {
                System.out.println("Không có khách hàng nào trong hệ thống!");
                return -1;
            }
            
            System.out.println("Danh sách khách hàng:");
            System.out.printf("%-5s | %-30s | %-20s | %-15s%n", "ID", "Tên", "Số điện thoại", "Email");
            System.out.println("----------------------------------------------------------------------");
            
            for (Customer customer : customers) {
                System.out.printf("%-5d | %-30s | %-20s | %-15s%n", 
                                  customer.getCustomerId(), 
                                  customer.getName(), 
                                  customer.getPhone(), 
                                  customer.getEmail());
            }
            
            System.out.print("\nNhập ID khách hàng (0 để hủy): ");
            int customerId = Integer.parseInt(scanner.nextLine());
            
            if (customerId == 0) {
                return -1;
            }
            
            // Kiểm tra xem ID khách hàng có tồn tại không
            Customer selected = customerDAO.getCustomerById(customerId);
            if (selected != null) {
                System.out.println("Đã chọn khách hàng: " + selected.getName());
                return customerId;
            } else {
                System.out.println("Không tìm thấy khách hàng với ID: " + customerId);
                return selectCustomer(); // Yêu cầu chọn lại
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách khách hàng: " + e.getMessage());
            return -1;
        } catch (NumberFormatException e) {
            System.out.println("ID không hợp lệ, vui lòng nhập lại!");
            return selectCustomer(); // Yêu cầu chọn lại
        }
    }
    
    private InvoiceDetail addItemToInvoice() {
        System.out.println("\n--- Thêm sản phẩm vào hóa đơn ---");
        
        // Hiển thị danh sách sản phẩm
        List<MenuItem> menuItems = menuItemDAO.getAllItems();
        if (menuItems.isEmpty()) {
            System.out.println("Không có sản phẩm nào trong hệ thống!");
            return null;
        }
        
        System.out.println("Danh sách sản phẩm:");
        System.out.printf("%-5s | %-30s | %-12s | %-15s | %-10s | %-10s%n", 
                         "ID", "Tên sản phẩm", "Giá", "Danh mục", "Trạng thái", "Tồn kho");
        System.out.println("--------------------------------------------------------------------------------------");
        
        for (MenuItem item : menuItems) {
            if ("active".equals(item.getStatus())) {
                int stockQuantity = inventoryDAO.getStockQuantity(item.getItemId());
                System.out.printf("%-5d | %-30s | %-12s | %-15s | %-10s | %-10d%n", 
                                 item.getItemId(), 
                                 item.getName(), 
                                 item.getUnitPrice(), 
                                 item.getCategory(), 
                                 item.getStatus(),
                                 stockQuantity);
            }
        }
        
        try {
            // Chọn sản phẩm
            System.out.print("\nNhập ID sản phẩm (0 để hủy): ");
            int itemId = Integer.parseInt(scanner.nextLine());
            
            if (itemId == 0) {
                return null;
            }
            
            // Kiểm tra sản phẩm tồn tại và còn hoạt động
            MenuItem selectedItem = menuItemDAO.getItemById(itemId);
            if (selectedItem == null || !"active".equals(selectedItem.getStatus())) {
                System.out.println("Sản phẩm không tồn tại hoặc không còn hoạt động!");
                return null;
            }
            
            // Hiển thị thông tin sản phẩm và tồn kho
            System.out.println("Sản phẩm: " + selectedItem.getName());
            System.out.println("Giá: " + selectedItem.getUnitPrice());
            
            // Lấy thông tin tồn kho
            int stockQuantity = inventoryDAO.getStockQuantity(itemId);
            System.out.println("Số lượng tồn kho: " + stockQuantity);
            
            // Nhập số lượng
            System.out.print("Nhập số lượng mua: ");
            int quantity = Integer.parseInt(scanner.nextLine());
            
            if (quantity <= 0) {
                System.out.println("Số lượng phải lớn hơn 0!");
                return null;
            }
            
            if (quantity > stockQuantity) {
                System.out.println("Số lượng vượt quá tồn kho!");
                return null;
            }
            
            // Tính toán thành tiền
            BigDecimal unitPrice = selectedItem.getUnitPrice();
            BigDecimal lineTotal = unitPrice.multiply(new BigDecimal(quantity));
            
            System.out.println("Thành tiền: " + lineTotal);
            
            // Tạo chi tiết hóa đơn
            InvoiceDetail detail = new InvoiceDetail();
            detail.setItemId(itemId); // Sử dụng setItemId thay vì setProductId
            detail.setQuantity(quantity);
            detail.setUnitPrice(unitPrice);
            detail.setLineTotal(lineTotal); // Sử dụng setLineTotal thay vì setSubtotal
            detail.setMenuItem(selectedItem); // Lưu thông tin MenuItem để sử dụng sau này
            
            return detail;
            
        } catch (NumberFormatException e) {
            System.out.println("Giá trị không hợp lệ, vui lòng thử lại!");
            return null;
        }
    }
    
    private void viewInvoices() {
        InvoiceUI invoiceUI = new InvoiceUI();
        invoiceUI.showUI();
    }
    
    private void viewMenuItems() {
        System.out.println("\n================= DANH SÁCH SẢN PHẨM =================");
        
        List<MenuItem> menuItems = menuItemDAO.getAllItems();
        if (menuItems.isEmpty()) {
            System.out.println("Không có sản phẩm nào trong hệ thống!");
            return;
        }
        
        System.out.printf("%-5s | %-30s | %-12s | %-15s | %-10s%n", 
                         "ID", "Tên sản phẩm", "Giá", "Danh mục", "Trạng thái");
        System.out.println("------------------------------------------------------------------------");
        
        for (MenuItem item : menuItems) {
            System.out.printf("%-5d | %-30s | %-12s | %-15s | %-10s%n", 
                             item.getItemId(), 
                             item.getName(), 
                             item.getUnitPrice(), 
                             item.getCategory(), 
                             item.getStatus());
        }
        
        System.out.print("\nNhấn Enter để quay lại...");
        scanner.nextLine();
    }
    
    private void manageInventory() {
        InventoryUI inventoryUI = new InventoryUI();
        inventoryUI.displayMenu();
    }
} 