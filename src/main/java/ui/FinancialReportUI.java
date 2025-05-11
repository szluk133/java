package ui;

import dao.FinancialReportDAO;
import model.MonthlyFinancialSummary;

import java.util.List;
import java.util.Scanner;

public class FinancialReportUI {
    private final Scanner scanner = new Scanner(System.in);
    private final FinancialReportDAO financialReportDAO = new FinancialReportDAO();
    
    public void displayMenu() {
        int choice;
        do {
            System.out.println("\n==================== BÁO CÁO TÀI CHÍNH ====================");
            System.out.println("1. Xem báo cáo lãi lỗ theo tháng");
            System.out.println("2. Xem tổng quan năm hiện tại");
            System.out.println("3. Tìm kiếm báo cáo theo tháng");
            System.out.println("0. Quay lại");
            System.out.print("Chọn chức năng: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        displayMonthlyReports();
                        break;
                    case 2:
                        displayYearOverview();
                        break;
                    case 3:
                        searchByMonth();
                        break;
                    case 0:
                        System.out.println(">> Quay lại menu trước.");
                        break;
                    default:
                        System.out.println(">> Lựa chọn không hợp lệ, vui lòng chọn lại.");
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
    
    private void displayMonthlyReports() {
        System.out.println("\n==================== BÁO CÁO LÃI LỖ THEO THÁNG ====================");
        List<MonthlyFinancialSummary> reports = financialReportDAO.getAllMonthlyReports();
        
        if (reports.isEmpty()) {
            System.out.println(">> Không có dữ liệu báo cáo tài chính.");
            return;
        }
        
        System.out.printf("%-10s | %-12s | %-12s | %-12s | %-12s | %-12s | %-20s\n", 
                "THÁNG", "DOANH THU", "CHI PHÍ NHẬP", "LƯƠNG NHÂN VIÊN", "THUÊ MẶT BẰNG", "LỢI NHUẬN", "CẬP NHẬT");
        System.out.println("----------------------------------------------------------------------------------------------------");
        
        for (MonthlyFinancialSummary report : reports) {
            System.out.printf("%-10s | %,12.2f | %,12.2f | %,12.2f | %,12.2f | %,12.2f | %-20s\n",
                    report.getReportMonth(),
                    report.getMonthlyRevenue(),
                    report.getMonthlyPurchaseCost(),
                    report.getMonthlySalaryCost(),
                    report.getMonthlyRent(),
                    report.getMonthlyProfit(),
                    report.getCalculatedAt());
        }
    }
    
    private void displayYearOverview() {
        System.out.println("\n==================== TỔNG QUAN NĂM HIỆN TẠI ====================");
        String currentYear = java.time.Year.now().toString();
        Object[] yearData = financialReportDAO.getYearlySummary(currentYear);
        
        if (yearData[0] == null) {
            System.out.println(">> Không có dữ liệu báo cáo tài chính cho năm " + currentYear);
            return;
        }
        
        double totalRevenue = (Double) yearData[0];
        double totalPurchase = (Double) yearData[1];
        double totalSalary = (Double) yearData[2];
        double totalRent = (Double) yearData[3];
        double totalProfit = (Double) yearData[4];
        
        System.out.println("NĂM: " + currentYear);
        System.out.println("------------------------------------");
        System.out.printf("Tổng doanh thu:      %,15.2f $\n", totalRevenue);
        System.out.printf("Tổng chi phí nhập:   %,15.2f $\n", totalPurchase);
        System.out.printf("Tổng chi phí lương:  %,15.2f $\n", totalSalary);
        System.out.printf("Tổng chi phí thuê:   %,15.2f $\n", totalRent);
        System.out.println("------------------------------------");
        System.out.printf("TỔNG LỢI NHUẬN:      %,15.2f $\n", totalProfit);
                
        // Tính phần trăm lợi nhuận trên doanh thu
        double profitMargin = totalRevenue > 0 ? (totalProfit / totalRevenue) * 100 : 0;
        System.out.printf("Tỷ suất lợi nhuận:   %15.2f %%\n", profitMargin);
    }
    
    private void searchByMonth() {
        System.out.println("\n==================== TÌM KIẾM BÁO CÁO ====================");
        System.out.print("Nhập tháng cần tìm (định dạng YYYY-MM): ");
        String month = scanner.nextLine();
        
        MonthlyFinancialSummary report = financialReportDAO.getMonthlyReport(month);
        
        if (report == null) {
            System.out.println(">> Không tìm thấy báo cáo cho tháng " + month);
            return;
        }
        
        System.out.println("\n==================== BÁO CÁO THÁNG " + month + " ====================");
        System.out.printf("Doanh thu:           %,15.2f $\n", report.getMonthlyRevenue());
        System.out.printf("Chi phí nhập hàng:   %,15.2f $\n", report.getMonthlyPurchaseCost());
        System.out.printf("Chi phí lương nhân viên:       %,15.2f $\n", report.getMonthlySalaryCost());
        System.out.printf("Chi phí thuê mặt bằng: %,13.2f $\n", report.getMonthlyRent());
        System.out.println("------------------------------------");
        System.out.printf("LỢI NHUẬN:           %,15.2f $\n", report.getMonthlyProfit());
        
        // Tính phần trăm lợi nhuận trên doanh thu
        double revenue = report.getMonthlyRevenue();
        double profit = report.getMonthlyProfit();
        double profitMargin = revenue > 0 ? (profit / revenue) * 100 : 0;
        System.out.printf("Tỷ suất lợi nhuận:   %15.2f %%\n", profitMargin);
        System.out.println("Cập nhật lần cuối:  " + report.getCalculatedAt());
    }
} 