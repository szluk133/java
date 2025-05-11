package ui;

import dao.InvoiceDAO;
import dao.InvoiceDetailDAO;
import dao.MenuItemDAO;
import dao.CustomerDAO;
import dao.EmployeeDAO;
import model.Invoice;
import model.InvoiceDetail;
import model.MenuItem;
import model.Customer;
import model.Employee;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class InvoiceUI {
    private Scanner scanner;
    private InvoiceDAO invoiceDAO;
    private InvoiceDetailDAO detailDAO;
    private MenuItemDAO menuItemDAO;
    private CustomerDAO customerDAO;
    private EmployeeDAO employeeDAO;
    
    public InvoiceUI() {
        scanner = new Scanner(System.in);
        invoiceDAO = new InvoiceDAO();
        detailDAO = new InvoiceDetailDAO();
        menuItemDAO = new MenuItemDAO();
        customerDAO = new CustomerDAO();
        employeeDAO = new EmployeeDAO();
    }
    
    public void showUI() {
        int choice;
        do {
            System.out.println("\n===================== QUẢN LÝ HÓA ĐƠN =====================");
            System.out.println("1. Xem danh sách hóa đơn");
            System.out.println("2. Xem chi tiết hóa đơn");
            System.out.println("3. Cập nhật trạng thái hóa đơn");
            System.out.println("0. Quay lại");
            System.out.print("Chọn chức năng: ");
            choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1:
                    displayAllInvoices();
                    break;
                case 2:
                    viewInvoiceDetails();
                    break;
                case 3:
                    updateInvoiceStatus();
                    break;
                case 0:
                    System.out.println("Quay lại menu trước...");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại.");
            }
        } while (choice != 0);
    }
    
    private void displayAllInvoices() {
        List<Invoice> invoices = invoiceDAO.getAllInvoices();
        
        if (invoices.isEmpty()) {
            System.out.println("Không có hóa đơn nào trong hệ thống.");
            return;
        }
        
        System.out.println("\n==================== DANH SÁCH HÓA ĐƠN ====================");
        System.out.printf("%-5s | %-20s | %-20s | %-20s | %-12s | %-10s%n", 
                "ID", "Khách hàng", "Nhân viên", "Ngày tạo", "Tổng tiền", "Trạng thái");
        System.out.println("-----------------------------------------------------------------------------------------");
        
        for (Invoice invoice : invoices) {
            String customerName = "Không xác định";
            String employeeName = "Không xác định";
            
            try {
                Customer customer = customerDAO.getCustomerById(invoice.getCustomerId());
                if (customer != null) {
                    customerName = customer.getName();
                }
                
                Employee employee = employeeDAO.getEmployeeById(invoice.getEmployeeId());
                if (employee != null) {
                    employeeName = employee.getFirstName() + " " + employee.getLastName();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            System.out.printf("%-5d | %-20s | %-20s | %-20s | %-12s | %-10s%n",
                    invoice.getInvoiceId(),
                    customerName,
                    employeeName,
                    invoice.getInvoiceDate(),
                    invoice.getTotalAmount(),
                    invoice.getStatus());
        }
    }
    
    private void viewInvoiceDetails() {
        System.out.print("\nNhập ID hóa đơn cần xem chi tiết: ");
        int invoiceId = Integer.parseInt(scanner.nextLine());
        
        Invoice invoice = invoiceDAO.getInvoiceById(invoiceId);
        if (invoice == null) {
            System.out.println("Không tìm thấy hóa đơn với ID: " + invoiceId);
            return;
        }
        
        // Hiển thị thông tin khách hàng và nhân viên
        String customerName = "Không xác định";
        String employeeName = "Không xác định";
        
        try {
            Customer customer = customerDAO.getCustomerById(invoice.getCustomerId());
            if (customer != null) {
                customerName = customer.getName();
            }
            
            Employee employee = employeeDAO.getEmployeeById(invoice.getEmployeeId());
            if (employee != null) {
                employeeName = employee.getFirstName() + " " + employee.getLastName();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        System.out.println("\nThông tin hóa đơn #" + invoiceId + ":");
        System.out.println("Khách hàng: " + customerName);
        System.out.println("Nhân viên: " + employeeName);
        System.out.println("Ngày tạo: " + invoice.getInvoiceDate());
        System.out.println("Trạng thái: " + invoice.getStatus());
        
        List<InvoiceDetail> details = detailDAO.getDetailsByInvoiceId(invoiceId);
        if (details.isEmpty()) {
            System.out.println("Hóa đơn này không có chi tiết nào.");
            return;
        }
        
        System.out.println("\n==================== CHI TIẾT HÓA ĐƠN ====================");
        System.out.printf("%-5s | %-8s | %-20s | %-8s | %-12s | %-12s%n", 
                "ID", "Mã món", "Tên món", "Số lượng", "Đơn giá", "Thành tiền");
        System.out.println("-------------------------------------------------------------------------");
        
        for (InvoiceDetail detail : details) {
            // Lấy thông tin từ MenuItem
            MenuItem menuItem = menuItemDAO.getItemById(detail.getItemId());
            String itemName = (menuItem != null) ? menuItem.getName() : "Món #" + detail.getItemId();
            
            System.out.printf("%-5d | %-8d | %-20s | %-8d | %-12s | %-12s%n",
                    detail.getDetailId(),
                    detail.getItemId(),
                    itemName,
                    detail.getQuantity(),
                    detail.getUnitPrice(),
                    detail.getLineTotal());
        }
        
        System.out.println("\nThông tin thanh toán:");
        System.out.println("Tổng tiền trước thuế: " + invoice.getTotalAmount());
        System.out.println("Thuế: " + invoice.getTax());
        System.out.println("Tổng tiền sau thuế: " + invoice.getDiscount());
    }
    
    private void updateInvoiceStatus() {
        System.out.print("\nNhập ID hóa đơn cần cập nhật trạng thái: ");
        int invoiceId = Integer.parseInt(scanner.nextLine());
        
        Invoice invoice = invoiceDAO.getInvoiceById(invoiceId);
        if (invoice == null) {
            System.out.println("Không tìm thấy hóa đơn với ID: " + invoiceId);
            return;
        }
        
        System.out.println("Trạng thái hiện tại: " + invoice.getStatus());
        System.out.println("\nCác trạng thái có thể:");
        System.out.println("1. Đang xử lý (pending)");
        System.out.println("2. Đã thanh toán (paid)");
        System.out.println("3. Hủy (cancelled)");
        
        System.out.print("Chọn trạng thái mới: ");
        int statusChoice = Integer.parseInt(scanner.nextLine());
        
        String newStatus;
        switch (statusChoice) {
            case 1:
                newStatus = "pending";
                break;
            case 2:
                newStatus = "paid";
                break;
            case 3:
                newStatus = "cancelled";
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
                return;
        }
        
        boolean success = invoiceDAO.updateInvoiceStatus(invoiceId, newStatus);
        if (success) {
            System.out.println("Cập nhật trạng thái thành công!");
        } else {
            System.out.println("Cập nhật trạng thái thất bại. Vui lòng thử lại sau.");
        }
    }
} 