package ui;

import dao.SupplierDAO;
import model.Employee;
import model.Supplier;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class SupplierUI {
    private static final SupplierDAO dao = new SupplierDAO();
    private static final Scanner scanner = new Scanner(System.in);

    public void menu() throws SQLException {
        int choice;
        do {
            System.out.println("\n=== Quản lý nhà cung cấp ===");
            System.out.println("1. Xem danh sách nhà cung cấp");
            System.out.println("2. Thêm nhà cung cấp");
            System.out.println("3. Sửa nhà cung cấp");
            System.out.println("4. Xóa nhà cung cấp");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> viewSuppliers();
                case 2 -> addSupplier();
                case 3 -> updateSupplier();
                case 4 -> deleteSupplier();
                default -> System.out.println("Lựa chọn không hợp lệ");
            }
        } while (choice != 0);
    }

    private static void viewSuppliers() throws SQLException {
        List<Supplier> suppliers = dao.getAllSuppliers();
        for (Supplier sup : suppliers) {
            System.out.println(sup);
        }
    }
    private void addSupplier() {
        System.out.print("Tên nhà cung cấp: ");
        String name = scanner.nextLine();
        System.out.print("Người liên hệ: ");
        String contact = scanner.nextLine();
        System.out.print("Số điện thoại: ");
        String phone = scanner.nextLine();
        System.out.print("Địa chỉ: ");
        String address = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        Supplier supplier = new Supplier(0, name, contact, phone, address, email);
        dao.addSupplier(supplier);
        System.out.println("Thêm nhà cung cấp thành công!");
    }


    private void updateSupplier() throws SQLException {
        System.out.print("Nhập ID nhà cung cấp muốn sửa: ");
        int id = Integer.parseInt(scanner.nextLine());
        Supplier sup = dao.getSupplierById(id);
        if (sup == null) {
            System.out.println("Không tìm thấy nhà cung cấp!");
            return;
        }
        System.out.println("Nếu không sửa hãy Enter");
        System.out.print("Tên nhà cung cấp mới (" + sup.getName() + "): ");
        String name = scanner.nextLine();
        if (!name.isBlank()) sup.setName(name);
        System.out.println("Nếu không sửa hãy Enter");
        System.out.print("Người liên hệ mới (" + sup.getContactPerson() + "): ");
        String contact = scanner.nextLine();
        if (!contact.isBlank()) sup.setContactPerson(contact);
        System.out.println("Nếu không sửa hãy Enter");
        System.out.print("Số điện thoại mới(" + sup.getPhone() + "):");
        String phone = scanner.nextLine();
        if (!phone.isBlank()) sup.setPhone(phone);
        System.out.println("Nếu không sửa hãy Enter");
        System.out.print("Địa chỉ mới (" + sup.getAddress() + "): ");
        String address = scanner.nextLine();
        if (!address.isBlank()) sup.setAddress(address);
        System.out.println("Nếu không sửa hãy Enter");
        System.out.print("Email mới (" + sup.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isBlank()) sup.setEmail(email);
        dao.updateSupplier(sup);
        System.out.println("Cập nhật thành công!");
    }

    private void deleteSupplier() {
        System.out.print("Nhập ID nhà cung cấp cần xóa ");
        int id = Integer.parseInt(scanner.nextLine());
        dao.deleteSupplier(id);
        System.out.println("Đã xóa nhà cung cấp");
    }
}
