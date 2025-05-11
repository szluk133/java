package ui;

import dao.EmployeeDAO;
import model.Employee;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class EmployeeManagerUI {
    private final Scanner scanner = new Scanner(System.in);
    private final EmployeeDAO employeeDAO = new EmployeeDAO();
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public void menu() {
        int choice;
        do {
            System.out.println("\n==================== QUẢN LÝ NHÂN VIÊN ====================");
            System.out.println("1. Xem danh sách nhân viên");
            System.out.println("2. Thêm nhân viên mới");
            System.out.println("3. Sửa thông tin nhân viên");
            System.out.println("4. Xóa nhân viên");
            System.out.println("0. Quay lại");
            System.out.print("Chọn chức năng: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1 -> viewAll();
                    case 2 -> addEmployee();
                    case 3 -> updateEmployee();
                    case 4 -> deleteEmployee();
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

    private void viewAll() {
        try {
            List<Employee> employees = employeeDAO.getAllEmployees();
            
            if (employees.isEmpty()) {
                System.out.println(">> Không có nhân viên nào trong hệ thống.");
                return;
            }
            
            displayEmployeesTable(employees);
        } catch (Exception e) {
            System.out.println(">> Lỗi khi lấy danh sách nhân viên: " + e.getMessage());
        }
    }
    
    private void displayEmployeesTable(List<Employee> employees) {
        System.out.println("\n==================== DANH SÁCH NHÂN VIÊN ====================");
        System.out.printf("%-5s | %-15s | %-15s | %-15s | %-25s | %-15s | %-25s | %-15s\n", 
                "ID", "HỌ", "TÊN", "CHỨC VỤ", "ĐỊA CHỈ", "SỐ ĐIỆN THOẠI", "EMAIL", "NGÀY VÀO LÀM");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
        
        for (Employee emp : employees) {
            String hireDate = emp.getHireDate() != null ? emp.getHireDate().format(dateFormatter) : "N/A";
                    
            System.out.printf("%-5d | %-15s | %-15s | %-15s | %-25s | %-15s | %-25s | %-15s\n",
                    emp.getEmployeeId(),
                    limitString(emp.getFirstName(), 15),
                    limitString(emp.getLastName(), 15),
                    limitString(emp.getPosition(), 15),
                    limitString(emp.getAddress(), 25),
                    emp.getPhone(),
                    limitString(emp.getEmail(), 25),
                    hireDate);
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Tổng số: " + employees.size() + " nhân viên");
    }
    
    private String limitString(String input, int maxLength) {
        if (input == null) return "";
        return input.length() <= maxLength ? input : input.substring(0, maxLength - 3) + "...";
    }

    private void addEmployee() {
        try {
            // Hiển thị danh sách nhân viên hiện tại
            List<Employee> employees = employeeDAO.getAllEmployees();
            if (!employees.isEmpty()) {
                displayEmployeesTable(employees);
            }
            
            System.out.println("\n==================== THÊM NHÂN VIÊN MỚI ====================");
            
            System.out.print("Họ: ");
            String firstName = scanner.nextLine();
            if (firstName.trim().isEmpty()) {
                System.out.println(">> Họ không được để trống!");
                return;
            }
            
            System.out.print("Tên: ");
            String lastName = scanner.nextLine();
            if (lastName.trim().isEmpty()) {
                System.out.println(">> Tên không được để trống!");
                return;
            }
            
            System.out.print("Chức vụ: ");
            String position = scanner.nextLine();
            
            System.out.print("Địa chỉ: ");
            String address = scanner.nextLine();
            
            System.out.print("Số điện thoại: ");
            String phone = scanner.nextLine();
            if (phone.trim().isEmpty()) {
                System.out.println(">> Số điện thoại không được để trống!");
                return;
            }
            
            // Kiểm tra số điện thoại đã tồn tại chưa
            for (Employee e : employees) {
                if (phone.equals(e.getPhone())) {
                    System.out.println(">> Số điện thoại này đã được sử dụng bởi nhân viên khác!");
                    return;
                }
            }
            
            System.out.print("Email: ");
            String email = scanner.nextLine();
            
            System.out.print("Ngày vào làm (dd-MM-yyyy): ");
            String hireDateStr = scanner.nextLine();
            LocalDate hireDate = null;
            
            if (!hireDateStr.trim().isEmpty()) {
                try {
                    hireDate = LocalDate.parse(hireDateStr, dateFormatter);
                } catch (DateTimeParseException e) {
                    System.out.println(">> Định dạng ngày không hợp lệ! Vui lòng sử dụng định dạng dd-MM-yyyy");
                    return;
                }
            }
            
            Employee employee = new Employee(0, firstName, lastName, position, address, phone, email, hireDate);
            employeeDAO.addEmployee(employee);
            System.out.println(">> Thêm nhân viên thành công!");
            
        } catch (SQLException e) {
            System.out.println(">> Lỗi khi thêm nhân viên: " + e.getMessage());
        }
    }

    private void updateEmployee() {
        try {
            // Hiển thị danh sách nhân viên hiện tại
            List<Employee> employees = employeeDAO.getAllEmployees();
            if (employees.isEmpty()) {
                System.out.println(">> Không có nhân viên nào trong hệ thống.");
                return;
            }
            
            displayEmployeesTable(employees);
            
            System.out.println("\n==================== CẬP NHẬT THÔNG TIN NHÂN VIÊN ====================");
            System.out.print("Nhập ID nhân viên cần sửa: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            Employee emp = employeeDAO.getEmployeeById(id);
            if (emp == null) {
                System.out.println(">> Không tìm thấy nhân viên với ID " + id);
                return;
            }

            System.out.println(">> Thông tin hiện tại của nhân viên:");
            System.out.println("ID: " + emp.getEmployeeId());
            System.out.println("Họ: " + emp.getFirstName());
            System.out.println("Tên: " + emp.getLastName());
            System.out.println("Chức vụ: " + emp.getPosition());
            System.out.println("Địa chỉ: " + emp.getAddress());
            System.out.println("Số điện thoại: " + emp.getPhone());
            System.out.println("Email: " + emp.getEmail());
            System.out.println("Ngày vào làm: " + (emp.getHireDate() != null ? emp.getHireDate().format(dateFormatter) : "N/A"));
            
            System.out.println("\n>> Nhập thông tin mới (ấn Enter để giữ nguyên):");
            
            System.out.print("Họ mới: ");
            String firstName = scanner.nextLine();
            if (!firstName.trim().isEmpty()) {
                emp.setFirstName(firstName);
            }

            System.out.print("Tên mới: ");
            String lastName = scanner.nextLine();
            if (!lastName.trim().isEmpty()) {
                emp.setLastName(lastName);
            }
            
            System.out.print("Chức vụ mới: ");
            String position = scanner.nextLine();
            if (!position.trim().isEmpty()) {
                emp.setPosition(position);
            }

            System.out.print("Địa chỉ mới: ");
            String address = scanner.nextLine();
            if (!address.trim().isEmpty()) {
                emp.setAddress(address);
            }

            System.out.print("Số điện thoại mới: ");
            String phone = scanner.nextLine();
            if (!phone.trim().isEmpty()) {
                // Kiểm tra số điện thoại đã tồn tại chưa
                boolean phoneExists = false;
                for (Employee other : employees) {
                    if (phone.equals(other.getPhone()) && other.getEmployeeId() != id) {
                        phoneExists = true;
                        break;
                    }
                }
                
                if (phoneExists) {
                    System.out.println(">> Số điện thoại này đã được sử dụng bởi nhân viên khác!");
                    return;
                }
                
                emp.setPhone(phone);
            }

            System.out.print("Email mới: ");
            String email = scanner.nextLine();
            if (!email.trim().isEmpty()) {
                emp.setEmail(email);
            }
            
            System.out.print("Ngày vào làm mới (dd-MM-yyyy): ");
            String hireDateStr = scanner.nextLine();
            if (!hireDateStr.trim().isEmpty()) {
                try {
                    LocalDate hireDate = LocalDate.parse(hireDateStr, dateFormatter);
                    emp.setHireDate(hireDate);
                } catch (DateTimeParseException e) {
                    System.out.println(">> Định dạng ngày không hợp lệ! Vui lòng sử dụng định dạng dd-MM-yyyy");
                    return;
                }
            }

            employeeDAO.updateEmployee(emp);
            System.out.println(">> Cập nhật thông tin nhân viên thành công!");
            
        } catch (NumberFormatException e) {
            System.out.println(">> Vui lòng nhập ID hợp lệ!");
        } catch (SQLException e) {
            System.out.println(">> Lỗi khi cập nhật thông tin nhân viên: " + e.getMessage());
        }
    }

    private void deleteEmployee() {
        try {
            // Hiển thị danh sách nhân viên hiện tại
            List<Employee> employees = employeeDAO.getAllEmployees();
            if (employees.isEmpty()) {
                System.out.println(">> Không có nhân viên nào trong hệ thống.");
                return;
            }
            
            displayEmployeesTable(employees);
            
            System.out.println("\n==================== XÓA NHÂN VIÊN ====================");
            System.out.print("Nhập ID nhân viên cần xóa: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            Employee emp = employeeDAO.getEmployeeById(id);
            if (emp == null) {
                System.out.println(">> Không tìm thấy nhân viên với ID " + id);
                return;
            }
            
            System.out.println(">> Thông tin nhân viên sẽ bị xóa:");
            System.out.println("ID: " + emp.getEmployeeId());
            System.out.println("Họ và tên: " + emp.getFirstName() + " " + emp.getLastName());
            System.out.println("Chức vụ: " + emp.getPosition());
            System.out.println("Số điện thoại: " + emp.getPhone());
            
            System.out.print(">> Bạn có chắc chắn muốn xóa nhân viên này? (y/n): ");
            String confirm = scanner.nextLine();
            
            if ("y".equalsIgnoreCase(confirm)) {
                employeeDAO.deleteEmployee(id);
                System.out.println(">> Đã xóa nhân viên thành công!");
            } else {
                System.out.println(">> Đã hủy thao tác xóa nhân viên.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println(">> Vui lòng nhập ID hợp lệ!");
        } catch (SQLException e) {
            System.out.println(">> Lỗi khi xóa nhân viên: " + e.getMessage());
        }
    }
}