package ui;

import java.util.Scanner;

public class AdminUI {
    private final Scanner scanner = new Scanner(System.in);

    public void displayAdminMenu() throws Exception {
        int choice;
        do {
            System.out.println("\n==================== QUẢN TRỊ VIÊN ====================");
            System.out.println("1. Quản lý nhân viên");
            System.out.println("2. Quản lý khách hàng");
            System.out.println("3. Quản lý nhập hàng");
            System.out.println("4. Quản lý hóa đơn");
            System.out.println("5. Quản lý nhà cung cấp");
            System.out.println("6. Quản lý tồn kho");
            System.out.println("7. Xem doanh thu");
            System.out.println("8. Quản lý lịch làm việc");
            System.out.println("9. Quản lý lương");
            System.out.println("10. Quản lý menu bán hàng");
            System.out.println("0. Đăng xuất");
            System.out.print("Chọn chức năng: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    EmployeeManagerUI employeeManagerUI = new EmployeeManagerUI();
                    employeeManagerUI.menu();
                    break;
                    // gọi StaffUI hoặc chức năng tương ứng

                case 2:
                    CustomerUI customerUI = new CustomerUI();
                    customerUI.menu();
                    break;
                case 3:
                    PurchaseOrderUI purchaseOrderUI = new PurchaseOrderUI();
                    purchaseOrderUI.menu();
                    break;
                case 4:
                    System.out.println(">> Điều hướng đến quản lý hóa đơn...");
                    InvoiceUI invoiceUI = new InvoiceUI();
                    invoiceUI.showUI();
                    break;
                case 5:
                    SupplierUI supplierUI = new SupplierUI();
                    supplierUI.menu();
                    break;
                case 6:
                    InventoryUI inventoryUI = new InventoryUI();
                    inventoryUI.displayMenu();
                    break;
                case 7:
                    FinancialReportUI financialReportUI = new FinancialReportUI();
                    financialReportUI.displayMenu();
                    break;
                case 8:
                    WorkScheduleUI workScheduleUI = new WorkScheduleUI();
                    workScheduleUI.showMenu();
                    break;
                case 9:
                    SalaryUI salaryUI = new SalaryUI();
                    salaryUI.displaySalaryMenu();
                    break;
                case 10:
                    MenuItemUI menuItemUI = new MenuItemUI();
                    menuItemUI.menu();
                    break;
                case 0:
                    System.out.println(">> Đăng xuất thành công!");
                    break;
                default:
                    System.out.println(">> Lựa chọn không hợp lệ, vui lòng chọn lại.");
            }
        } while (choice != 0);
    }
}
