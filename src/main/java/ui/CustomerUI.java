package ui;
import dao.CustomerDAO;
import model.Customer;

import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;

public class CustomerUI {
    private static final CustomerDAO dao = new CustomerDAO();
    private static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    
    public void menu() {
        int choice;
        do {
            System.out.println("\n==================== QUẢN LÝ KHÁCH HÀNG ====================");
            System.out.println("1. Xem danh sách khách hàng");
            System.out.println("2. Thêm khách hàng mới");
            System.out.println("3. Sửa thông tin khách hàng");
            System.out.println("4. Xóa khách hàng");
            System.out.println("0. Quay lại");
            System.out.print("Chọn chức năng: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1 -> viewCustomers();
                    case 2 -> addCustomer();
                    case 3 -> updateCustomer();
                    case 4 -> deleteCustomer();
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

    private void viewCustomers() {
        try {
            List<Customer> customers = dao.getAllCustomers();
            
            if (customers.isEmpty()) {
                System.out.println(">> Không có khách hàng nào trong hệ thống.");
                return;
            }
            
            displayCustomersTable(customers);
        } catch (SQLException e) {
            System.out.println(">> Lỗi khi lấy danh sách khách hàng: " + e.getMessage());
        }
    }
    
    private void displayCustomersTable(List<Customer> customers) {
        System.out.println("\n==================== DANH SÁCH KHÁCH HÀNG ====================");
        System.out.printf("%-5s | %-25s | %-30s | %-15s | %-25s | %-20s\n", 
                "ID", "TÊN KHÁCH HÀNG", "ĐỊA CHỈ", "SỐ ĐIỆN THOẠI", "EMAIL", "NGÀY ĐĂNG KÝ");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
        
        for (Customer c : customers) {
            String registeredAt = c.getRegisteredAt() != null ? 
                    dateFormat.format(c.getRegisteredAt()) : "N/A";
                    
            System.out.printf("%-5d | %-25s | %-30s | %-15s | %-25s | %-20s\n",
                    c.getCustomerId(),
                    limitString(c.getName(), 25),
                    limitString(c.getAddress(), 30),
                    c.getPhone(),
                    limitString(c.getEmail(), 25),
                    registeredAt);
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Tổng số: " + customers.size() + " khách hàng");
    }
    
    private String limitString(String input, int maxLength) {
        if (input == null) return "";
        return input.length() <= maxLength ? input : input.substring(0, maxLength - 3) + "...";
    }

    private void addCustomer() {
        try {
            // Hiển thị danh sách khách hàng hiện tại
            List<Customer> customers = dao.getAllCustomers();
            if (!customers.isEmpty()) {
                displayCustomersTable(customers);
            }
            
            System.out.println("\n==================== THÊM KHÁCH HÀNG MỚI ====================");
            System.out.print("Tên khách hàng: ");
            String name = scanner.nextLine();
            if (name.trim().isEmpty()) {
                System.out.println(">> Tên khách hàng không được để trống!");
                return;
            }
            
            System.out.print("Địa chỉ: ");
            String address = scanner.nextLine();
            
            System.out.print("Số điện thoại: ");
            String phone = scanner.nextLine();
            if (phone.trim().isEmpty()) {
                System.out.println(">> Số điện thoại không được để trống!");
                return;
            }
            
            // Kiểm tra số điện thoại đã tồn tại chưa
            for (Customer c : customers) {
                if (phone.equals(c.getPhone())) {
                    System.out.println(">> Số điện thoại này đã được sử dụng bởi khách hàng khác!");
                    return;
                }
            }
            
            System.out.print("Email: ");
            String email = scanner.nextLine();
            
            Customer customer = new Customer(0, name, address, phone, email, null);
            dao.addCustomer(customer);
            System.out.println(">> Thêm khách hàng thành công!");
            
        } catch (SQLException e) {
            System.out.println(">> Lỗi khi thêm khách hàng: " + e.getMessage());
        }
    }

    private void updateCustomer() {
        try {
            // Hiển thị danh sách khách hàng hiện tại
            List<Customer> customers = dao.getAllCustomers();
            if (customers.isEmpty()) {
                System.out.println(">> Không có khách hàng nào trong hệ thống.");
                return;
            }
            
            displayCustomersTable(customers);
            
            System.out.println("\n==================== CẬP NHẬT THÔNG TIN KHÁCH HÀNG ====================");
            System.out.print("Nhập ID khách hàng cần sửa: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            Customer c = dao.getCustomerById(id);
            if (c == null) {
                System.out.println(">> Không tìm thấy khách hàng với ID " + id);
                return;
            }

            System.out.println(">> Thông tin hiện tại của khách hàng:");
            System.out.println("ID: " + c.getCustomerId());
            System.out.println("Tên: " + c.getName());
            System.out.println("Địa chỉ: " + c.getAddress());
            System.out.println("Số điện thoại: " + c.getPhone());
            System.out.println("Email: " + c.getEmail());
            System.out.println("\n>> Nhập thông tin mới (ấn Enter để giữ nguyên):");
            
            System.out.print("Tên mới: ");
            String name = scanner.nextLine();
            if (!name.trim().isEmpty()) {
                c.setName(name);
            }

            System.out.print("Địa chỉ mới: ");
            String address = scanner.nextLine();
            if (!address.trim().isEmpty()) {
                c.setAddress(address);
            }

            System.out.print("Số điện thoại mới: ");
            String phone = scanner.nextLine();
            if (!phone.trim().isEmpty()) {
                // Kiểm tra số điện thoại đã tồn tại chưa
                boolean phoneExists = false;
                for (Customer other : customers) {
                    if (phone.equals(other.getPhone()) && other.getCustomerId() != id) {
                        phoneExists = true;
                        break;
                    }
                }
                
                if (phoneExists) {
                    System.out.println(">> Số điện thoại này đã được sử dụng bởi khách hàng khác!");
                    return;
                }
                
                c.setPhone(phone);
            }

            System.out.print("Email mới: ");
            String email = scanner.nextLine();
            if (!email.trim().isEmpty()) {
                c.setEmail(email);
            }

            dao.updateCustomer(c);
            System.out.println(">> Cập nhật thông tin khách hàng thành công!");
            
        } catch (NumberFormatException e) {
            System.out.println(">> Vui lòng nhập ID hợp lệ!");
        } catch (SQLException e) {
            System.out.println(">> Lỗi khi cập nhật thông tin khách hàng: " + e.getMessage());
        }
    }

    private void deleteCustomer() {
        try {
            // Hiển thị danh sách khách hàng hiện tại
            List<Customer> customers = dao.getAllCustomers();
            if (customers.isEmpty()) {
                System.out.println(">> Không có khách hàng nào trong hệ thống.");
                return;
            }
            
            displayCustomersTable(customers);
            
            System.out.println("\n==================== XÓA KHÁCH HÀNG ====================");
            System.out.print("Nhập ID khách hàng cần xóa: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            Customer c = dao.getCustomerById(id);
            if (c == null) {
                System.out.println(">> Không tìm thấy khách hàng với ID " + id);
                return;
            }
            
            System.out.println(">> Thông tin khách hàng sẽ bị xóa:");
            System.out.println("ID: " + c.getCustomerId());
            System.out.println("Tên: " + c.getName());
            System.out.println("Số điện thoại: " + c.getPhone());
            
            System.out.print(">> Bạn có chắc chắn muốn xóa khách hàng này? (y/n): ");
            String confirm = scanner.nextLine();
            
            if ("y".equalsIgnoreCase(confirm)) {
                dao.deleteCustomer(id);
                System.out.println(">> Đã xóa khách hàng thành công!");
            } else {
                System.out.println(">> Đã hủy thao tác xóa khách hàng.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println(">> Vui lòng nhập ID hợp lệ!");
        } catch (SQLException e) {
            System.out.println(">> Lỗi khi xóa khách hàng: " + e.getMessage());
        }
    }
}
