package ui;

import service.WorkScheduleService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class WorkScheduleUI {
    private final WorkScheduleService workScheduleService;
    private final Scanner scanner;

    public WorkScheduleUI() {
        this.workScheduleService = new WorkScheduleService();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n--- Quản lý Lịch Làm Việc ---");
            System.out.println("1. Thêm lịch làm việc cho nhân viên (Hôm nay)");
            System.out.println("2. Xem lịch làm việc theo tháng");
            System.out.println("0. Quay lại menu chính");
            System.out.print("Nhập lựa chọn của bạn: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        addScheduleForToday();
                        break;
                    case 2:
                        viewSchedulesByMonth();
                        break;
                    case 0:
                        System.out.println("Quay lại menu chính...");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Đầu vào không hợp lệ. Vui lòng nhập một số.");
                scanner.nextLine(); // Consume invalid input
                choice = -1; // Continue loop
            }

        } while (choice != 0);
    }

    private void addScheduleForToday() {
        System.out.println("\n--- Thêm Lịch Làm Việc cho Hôm Nay ---");
        try {
            System.out.print("Nhập ID nhân viên: ");
            int employeeId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Nhập ca làm việc (1=Sáng sớm, 2=Sáng muộn, 3=Chiều sớm, 4=Chiều muộn): ");
            int shiftNumber = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            boolean success = workScheduleService.addWorkSchedule(employeeId, shiftNumber);

            if (success) {
                System.out.println("Đã thêm lịch làm việc thành công cho nhân viên ID " + employeeId + " vào ca " + shiftNumber + " cho ngày hôm nay.");
            } else {
                System.out.println("Không thể thêm lịch làm việc. Vui lòng kiểm tra lại thông tin hoặc lỗi hệ thống.");
            }
        } catch (InputMismatchException e) {
            System.out.println("ID nhân viên hoặc ca làm việc không hợp lệ. Vui lòng nhập số.");
            scanner.nextLine(); // Consume invalid input
        }
    }

    private void viewSchedulesByDate() {
        System.out.println("\n--- Xem Lịch Làm Việc Theo Ngày ---");
        System.out.print("Nhập ngày cần xem (YYYY-MM-DD): ");
        String dateString = scanner.nextLine();

        try {
            LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
            List<Map<String, Object>> schedules = workScheduleService.getSchedulesByDate(date);

            if (schedules.isEmpty()) {
                System.out.println("Không tìm thấy lịch làm việc nào cho ngày " + dateString);
            } else {
                System.out.println("\nLịch làm việc ngày: " + dateString);
                System.out.println("-----------------------------------------------------------");
                System.out.printf("%-5s | %-8s | %-20s | %-10s%n", "Ca", "ID NV", "Tên Nhân Viên", "ScheduleID");
                System.out.println("-----------------------------------------------------------");
                for (Map<String, Object> schedule : schedules) {
                    String employeeName = schedule.get("employeeFirstName") + " " + schedule.get("employeeLastName");
                    System.out.printf("%-5s | %-8s | %-20s | %-10s%n",
                            schedule.get("shiftNumber"),
                            schedule.get("employeeId"),
                            employeeName,
                            schedule.get("scheduleId")
                    );
                }
                System.out.println("-----------------------------------------------------------");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Định dạng ngày không hợp lệ. Vui lòng sử dụng định dạng YYYY-MM-DD.");
        } catch (Exception e) {
            System.err.println("Đã xảy ra lỗi khi xem lịch làm việc: " + e.getMessage());
            e.printStackTrace(); // Log the full stack trace for debugging
        }
    }

    private void viewSchedulesByMonth() {
        System.out.println("\n--- Xem Lịch Làm Việc Theo Tháng ---");
        try {
            System.out.print("Nhập năm (YYYY): ");
            int year = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Nhập tháng (1-12): ");
            int month = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (month < 1 || month > 12) {
                System.out.println("Tháng không hợp lệ. Vui lòng nhập giá trị từ 1 đến 12.");
                return;
            }

            List<Map<String, Object>> schedules = workScheduleService.getSchedulesByMonth(year, month);

            if (schedules.isEmpty()) {
                System.out.printf("Không tìm thấy lịch làm việc nào cho tháng %02d/%d%n", month, year);
            } else {
                System.out.printf("\nLịch làm việc tháng: %02d/%d%n", month, year);
                System.out.println("-----------------------------------------------------------");
                LocalDate currentDate = null;
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                for (Map<String, Object> schedule : schedules) {
                    LocalDate workDate = (LocalDate) schedule.get("workDate");
                    if (!workDate.equals(currentDate)) {
                        if (currentDate != null) {
                            System.out.println("-----------------------------------------------------------"); // Separator between days
                        }
                        currentDate = workDate;
                        System.out.println("Ngày: " + currentDate.format(dateFormatter));
                        System.out.printf("  %-5s | %-8s | %-20s%n", "Ca", "ID NV", "Tên Nhân Viên");
                        System.out.println("  -----------------------------------------");
                    }

                    String employeeName = schedule.get("employeeFirstName") + " " + schedule.get("employeeLastName");
                    System.out.printf("  %-5s | %-8s | %-20s%n",
                            schedule.get("shiftNumber"),
                            schedule.get("employeeId"),
                            employeeName
                    );
                }
                System.out.println("-----------------------------------------------------------");
            }
        } catch (InputMismatchException e) {
            System.out.println("Năm hoặc tháng không hợp lệ. Vui lòng nhập số.");
            scanner.nextLine(); // Consume invalid input
        } catch (Exception e) {
            System.err.println("Đã xảy ra lỗi khi xem lịch làm việc theo tháng: " + e.getMessage());
            e.printStackTrace(); // Log the full stack trace for debugging
        }
    }

    // You would call showMenu() from your main AdminUI or Application entry point
    // Example (if running this UI directly):
    /*
    public static void main(String[] args) {
        WorkScheduleUI ui = new WorkScheduleUI();
        ui.showMenu();
    }
    */
} 