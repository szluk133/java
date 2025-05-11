package ui;

import model.Employee;
import model.Salary;
import service.SalaryService;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class SalaryUI {
    private final SalaryService salaryService;
    private final Scanner scanner;
    private final NumberFormat currencyFormatter;

    public SalaryUI() {
        this.salaryService = new SalaryService(); // Consider dependency injection
        this.scanner = new Scanner(System.in);
        // Use Locale.US for USD formatting
        this.currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
    }

    public void displaySalaryMenu() {
        int choice;
        do {
            System.out.println("\n--- Quản lý lương ---");
            System.out.println("1. Xem bảng lương nhân viên");
            System.out.println("2. Xem bảng lương tất cả nhân viên");
            System.out.println("0. Quay lại menu chính");
            System.out.print("Chọn chức năng: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        viewEmployeeSalary();
                        break;
                    case 2:
                        viewAllEmployeesSalaries();
                        break;
                    case 0:
                        System.out.println("Trở lại menu chính...");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số.");
                choice = -1; // Continue loop
            }
        } while (choice != 0);
    }

    private void viewEmployeeSalary() {
        System.out.print("Nhập ID nhân viên cần xem lương: ");
        int employeeId;
        try {
            employeeId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID nhân viên không hợp lệ.");
            return;
        }

        Salary salary = salaryService.getEmployeeSalaryDetails(employeeId);

        if (salary != null && salary.getEmployee() != null) {
            Employee employee = salary.getEmployee();
            System.out.println("\n--- Bảng lương chi tiết ---");
            System.out.println("ID Nhân viên: " + employee.getEmployeeId());
            System.out.println("Tên nhân viên: " + employee.getFirstName() + " " + employee.getLastName());
            System.out.println("Chức vụ: " + employee.getPosition());
            System.out.println("Lương cơ bản: " + formatCurrency(salary.getBaseSalary()));
            System.out.println("Số ca làm việc: " + salary.getShiftsWorked());
            System.out.println("Lương tháng thực nhận: " + formatCurrency(salary.getMonthlySalary()));
            System.out.println("---------------------------");
        } else if (salary != null) {
             System.out.println("\n--- Bảng lương chi tiết (Thiếu thông tin nhân viên) ---");
             System.out.println("ID Nhân viên (từ bảng lương): " + salary.getEmployeeId());
             System.out.println("Lương cơ bản: " + formatCurrency(salary.getBaseSalary()));
             System.out.println("Số ca làm việc: " + salary.getShiftsWorked());
             System.out.println("Lương tháng thực nhận: " + formatCurrency(salary.getMonthlySalary()));
             System.out.println("Lỗi: Không tìm thấy thông tin chi tiết của nhân viên với ID = " + salary.getEmployeeId());
             System.out.println("---------------------------");
        } else {
            System.out.println("Không tìm thấy thông tin lương cho nhân viên có ID: " + employeeId);
        }
    }
    
    /**
     * Hiển thị bảng lương của tất cả nhân viên
     */
    private void viewAllEmployeesSalaries() {
        List<Salary> allSalaries = salaryService.getAllEmployeeSalaryDetails();
        
        if (allSalaries.isEmpty()) {
            System.out.println("Không có dữ liệu lương nhân viên nào.");
            return;
        }
        
        System.out.println("\n====== BẢNG LƯƠNG TOÀN BỘ NHÂN VIÊN ======");
        System.out.printf("%-5s | %-25s | %-15s | %-8s | %-15s\n", 
                "ID", "Họ và tên", "Lương cơ bản", "Số ca", "Lương tháng");
        System.out.println("-----------------------------------------------------------------------");
        
        for (Salary salary : allSalaries) {
            String employeeName = "Không xác định";
            
            if (salary.getEmployee() != null) {
                Employee emp = salary.getEmployee();
                employeeName = emp.getFirstName() + " " + emp.getLastName();
            }
            
            System.out.printf("%-5d | %-25s | %-15s | %-8d | %-15s\n",
                    salary.getEmployeeId(),
                    employeeName,
                    formatCurrency(salary.getBaseSalary()),
                    salary.getShiftsWorked(),
                    formatCurrency(salary.getMonthlySalary()));
        }
        
        System.out.println("-----------------------------------------------------------------------");
    }

    private String formatCurrency(BigDecimal amount) {
        if (amount == null) {
            return "N/A";
        }
        return currencyFormatter.format(amount);
    }
} 