package ui;

import dao.InvoiceDAO;
import dao.InvoiceDetailDAO;
import dao.MenuItemDAO;
import model.Invoice;
import model.InvoiceDetail;
import model.MenuItem;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.math.BigDecimal;

public class CustomerInvoiceUI {
    private final Scanner scanner = new Scanner(System.in);
    private final InvoiceDAO invoiceDAO = new InvoiceDAO();
    private final InvoiceDetailDAO invoiceDetailDAO = new InvoiceDetailDAO();
    private final MenuItemDAO menuItemDAO = new MenuItemDAO();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    private final int customerId;

    public CustomerInvoiceUI(int customerId) {
        this.customerId = customerId;
    }

    public void displayMenu() {
        int choice;
        do {
            System.out.println("\n==================== LỊCH SỬ MUA HÀNG ====================");
            System.out.println("1. Xem danh sách hóa đơn");
            System.out.println("2. Xem chi tiết hóa đơn");
            System.out.println("0. Quay lại");
            System.out.print("Chọn chức năng: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1 -> viewInvoices();
                    case 2 -> viewInvoiceDetails();
                    case 0 -> System.out.println(">> Quay lại menu trước.");
                    default -> System.out.println(">> Lựa chọn không hợp lệ, vui lòng chọn lại.");
                }
            } catch (NumberFormatException e) {
                System.out.println(">> Vui lòng nhập số.");
                choice = -1;
            } catch (Exception e) {
                System.out.println(">> Đã xảy ra lỗi: " + e.getMessage());
                choice = -1;
            }
        } while (choice != 0);
    }

    private void viewInvoices() {
        try {
            List<Invoice> invoices = invoiceDAO.getInvoicesByCustomerId(customerId);
            
            if (invoices.isEmpty()) {
                System.out.println(">> Bạn chưa có hóa đơn nào.");
                return;
            }
            
            displayInvoicesTable(invoices);
        } catch (Exception e) {
            System.out.println(">> Lỗi khi lấy danh sách hóa đơn: " + e.getMessage());
        }
    }
    
    private void displayInvoicesTable(List<Invoice> invoices) {
        System.out.println("\n==================== DANH SÁCH HÓA ĐƠN ====================");
        System.out.printf("%-5s | %-20s | %-15s | %-15s | %-15s | %-10s\n", 
                "ID", "NGÀY MUA", "TỔNG TIỀN", "THUẾ", "GIẢM GIÁ", "TRẠNG THÁI");
        System.out.println("-------------------------------------------------------------------------");
        
        for (Invoice invoice : invoices) {
            String invoiceDate = invoice.getInvoiceDate() != null ? 
                    dateFormat.format(invoice.getInvoiceDate()) : "N/A";
            
            System.out.printf("%-5d | %-20s | %-15.2f | %-15.2f | %-15.2f | %-10s\n",
                    invoice.getInvoiceId(),
                    invoiceDate,
                    invoice.getTotalAmount(),
                    invoice.getTax(),
                    invoice.getDiscount(),
                    invoice.getStatus());
        }
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Tổng số: " + invoices.size() + " hóa đơn");
    }
    
    private void viewInvoiceDetails() {
        try {
            List<Invoice> invoices = invoiceDAO.getInvoicesByCustomerId(customerId);
            
            if (invoices.isEmpty()) {
                System.out.println(">> Bạn chưa có hóa đơn nào.");
                return;
            }
            
            displayInvoicesTable(invoices);
            
            System.out.print("\nNhập ID hóa đơn cần xem chi tiết: ");
            int invoiceId = Integer.parseInt(scanner.nextLine());
            
            // Kiểm tra xem hóa đơn có thuộc về khách hàng này không
            boolean isValidInvoice = false;
            for (Invoice inv : invoices) {
                if (inv.getInvoiceId() == invoiceId) {
                    isValidInvoice = true;
                    break;
                }
            }
            
            if (!isValidInvoice) {
                System.out.println(">> Hóa đơn không tồn tại hoặc không thuộc về bạn.");
                return;
            }
            
            // Lấy thông tin chi tiết hóa đơn
            Invoice invoice = invoiceDAO.getInvoiceById(invoiceId);
            List<InvoiceDetail> details = invoiceDetailDAO.getDetailsByInvoiceId(invoiceId);
            
            if (details.isEmpty()) {
                System.out.println(">> Không có chi tiết nào cho hóa đơn này.");
                return;
            }
            
            displayInvoiceDetailsTable(invoice, details);
            
        } catch (NumberFormatException e) {
            System.out.println(">> Vui lòng nhập số ID hợp lệ.");
        } catch (Exception e) {
            System.out.println(">> Lỗi khi lấy chi tiết hóa đơn: " + e.getMessage());
        }
    }
    
    private void displayInvoiceDetailsTable(Invoice invoice, List<InvoiceDetail> details) {
        System.out.println("\n==================== CHI TIẾT HÓA ĐƠN #" + invoice.getInvoiceId() + " ====================");
        System.out.println("Ngày mua: " + dateFormat.format(invoice.getInvoiceDate()));
        System.out.println("Trạng thái: " + invoice.getStatus());
        System.out.println("\nCác sản phẩm đã mua:");
        System.out.printf("%-5s | %-30s | %-10s | %-15s | %-15s\n", 
                "STT", "TÊN SẢN PHẨM", "SỐ LƯỢNG", "ĐƠN GIÁ", "THÀNH TIỀN");
        System.out.println("--------------------------------------------------------------------------------");
        
        int stt = 1;
        for (InvoiceDetail detail : details) {
            // Lấy tên sản phẩm từ item_id
            String itemName = "Unknown";
            try {
                MenuItem item = menuItemDAO.getMenuItemById(detail.getItemId());
                if (item != null) {
                    itemName = item.getName();
                }
            } catch (Exception e) {
                // Xử lý ngoại lệ nếu không thể lấy tên sản phẩm
            }
            
            System.out.printf("%-5d | %-30s | %-10d | %-15.2f | %-15.2f\n",
                    stt++,
                    limitString(itemName, 30),
                    detail.getQuantity(),
                    detail.getUnitPrice(),
                    detail.getLineTotal());
        }
        
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-52s | %-15s | %-15.2f\n", "", "Tổng cộng:", invoice.getTotalAmount());
        System.out.printf("%-52s | %-15s | %-15.2f\n", "", "Thuế:", invoice.getTax());
        System.out.printf("%-52s | %-15s | %-15.2f\n", "", "Giảm giá:", invoice.getDiscount());
        System.out.printf("%-52s | %-15s | %-15.2f\n", "", "Thành tiền:", 
                invoice.getTotalAmount().add(invoice.getTax()).subtract(invoice.getDiscount()));
    }
    
    private String limitString(String input, int maxLength) {
        if (input == null) return "";
        return input.length() <= maxLength ? input : input.substring(0, maxLength - 3) + "...";
    }
} 