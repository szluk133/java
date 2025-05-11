package ui;

import model.User;
import service.AuthService;

import java.util.Scanner;

public class LoginUI {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        AuthService authService = new AuthService();
        User loggedInUser = null;
        boolean exitSystem = false;

        while (!exitSystem) {
            System.out.println("\n============== HỆ THỐNG QUẢN LÝ CỬA HÀNG ==============");
            System.out.println("1. Đăng nhập");
            System.out.println("0. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
                continue;
            }

            switch (choice) {
                case 1:
                    loggedInUser = performLogin(sc, authService);
                    if (loggedInUser != null) {
                        handleUserLogin(loggedInUser);
                    }
                    break;
                case 0:
                    exitSystem = true;
                    System.out.println("Cảm ơn bạn đã sử dụng hệ thống. Tạm biệt!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }

    private static User performLogin(Scanner sc, AuthService authService) {
        System.out.println("\n------------- Đăng nhập -------------");
        
        User loggedInUser = null;
        boolean continueLogin = true;
        
        while (continueLogin) {
            System.out.print("Tên đăng nhập: ");
            String username = sc.nextLine();

            System.out.print("Mật khẩu: ");
            String password = sc.nextLine();

            loggedInUser = authService.login(username, password);

            if (loggedInUser != null) {
                System.out.println("Đăng nhập thành công!");
                continueLogin = false; // Đăng nhập thành công, thoát vòng lặp
            } else {
                System.out.println("Đăng nhập thất bại. Tên đăng nhập hoặc mật khẩu không đúng.");
                System.out.print("Bạn có muốn thử lại không? (y/n): ");
                String retry = sc.nextLine();
                if (!retry.equalsIgnoreCase("y")) {
                    continueLogin = false; // Người dùng không muốn thử lại, thoát vòng lặp
                }
            }
        }
        
        return loggedInUser;
    }

    private static void handleUserLogin(User loggedInUser) throws Exception {
        switch (loggedInUser.getRoleId()) {
            case 1: // Admin
                System.out.println(">> Chào mừng quản trị viên " + loggedInUser.getUsername() + "!");
                AdminUI adminUI = new AdminUI();
                adminUI.displayAdminMenu();
                break;
            case 2: // Nhân viên1
                System.out.println(">> Chào mừng nhân viên " + loggedInUser.getUsername() + "!");
                int employeeId = loggedInUser.getEmployeeId() != null ? loggedInUser.getEmployeeId() : 0;
                StaffUI staffUI = new StaffUI(employeeId);
                staffUI.displayStaffMenu();
                break;
            case 3: // Khách hàng
                System.out.println(">> Chào mừng khách hàng " + loggedInUser.getUsername() + "!");
                
                // Kiểm tra xem có customer_id không
                Integer customerId = loggedInUser.getCustomerId();
                if (customerId == null || customerId == 0) {
                    System.out.println(">> Không thể xác định thông tin khách hàng. Vui lòng liên hệ quản trị viên.");
                    return;
                }
                
                // Hiển thị giao diện quản lý hóa đơn cho khách hàng
                CustomerInvoiceUI customerInvoiceUI = new CustomerInvoiceUI(customerId);
                customerInvoiceUI.displayMenu();
                break;
            default:
                System.out.println(">> Vai trò không xác định.");
        }
    }
}

