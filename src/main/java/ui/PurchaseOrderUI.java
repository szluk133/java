package ui;

import dao.DatabaseConnection;
import dao.PurchaseOrderDAO;
import model.PurchaseOrder;
import ui.PurchaseOrderDetailUI;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class PurchaseOrderUI {
    private Scanner scanner = new Scanner(System.in);
    private PurchaseOrderDAO purchaseOrderDAO = new PurchaseOrderDAO();
    private PurchaseOrderDetailUI purchaseOrderDetailUI = new PurchaseOrderDetailUI();

    public void menu() {
        while (true) {
            System.out.println("\n=== QUẢN LÝ ĐƠN MUA HÀNG ===");
            System.out.println("1. Tạo đơn mua hàng mới");
            System.out.println("2. Cập nhật trạng thái đơn");
            System.out.println("3. Xem tất cả đơn mua");
            System.out.println("4. Xem chi tiết đơn mua");
            System.out.println("5. Huỷ đơn mua");
            System.out.println("0. Quay lại");
            System.out.print("Chọn chức năng: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1: createNewOrder(); break;
                    case 2: updateStatus(); break;
                    case 3: viewAll(); break;
                    case 4: viewOrderDetails(); break;
                    case 5: cancelOrder(); break;
                    case 0: return;
                    default: System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số!");
            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    
    private void createNewOrder() {
        try {
            // Hiển thị danh sách nhà cung cấp
            System.out.println("\n------ Danh sách nhà cung cấp ------");
            Connection conn = DatabaseConnection.getConnection();
            String supplierQuery = "SELECT supplier_id, name FROM suppliers";
            PreparedStatement stmt = conn.prepareStatement(supplierQuery);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                System.out.printf("%d - %s\n", rs.getInt("supplier_id"), rs.getString("name"));
            }
            
            System.out.print("\nNhập mã nhà cung cấp: ");
            int supplierId = Integer.parseInt(scanner.nextLine());
            
            // Tạo đơn hàng mới với trạng thái mặc định là "received"
            PurchaseOrder order = new PurchaseOrder();
            order.setSupplierId(supplierId);
            
            // Sử dụng ngày hiện tại
            LocalDate currentDate = LocalDate.now();
            order.setExpectedDate(Date.valueOf(currentDate));
            order.setStatus("received");
            
            // Lưu đơn hàng và lấy ID
            int newOrderId = purchaseOrderDAO.createPurchaseOrder(order);
            
            if (newOrderId > 0) {
                System.out.println("Đã tạo đơn hàng mới với mã: " + newOrderId);
                System.out.println("\nBây giờ hãy nhập chi tiết sản phẩm cho đơn hàng");
                
                // Chuyển sang quản lý chi tiết đơn hàng
                purchaseOrderDetailUI.addItemsToOrder(newOrderId);
            } else {
                System.out.println("Không thể tạo đơn hàng!");
            }
            
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (SQLException e) {
            System.out.println("Lỗi khi tạo đơn hàng: " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Vui lòng nhập số hợp lệ!");
        }
    }

    private void updateStatus() {
        try {
            System.out.print("Nhập mã đơn mua cần cập nhật: ");
            int id = Integer.parseInt(scanner.nextLine());

            PurchaseOrder po = purchaseOrderDAO.getPurchaseOrderById(id);
            if (po == null) {
                System.out.println("Không tìm thấy đơn mua.");
                return;
            }

            System.out.println("Trạng thái hiện tại: " + po.getStatus());
            System.out.println("Chọn trạng thái mới:");
            System.out.println("1. Đang chờ (pending)");
            System.out.println("2. Đã nhận (received)");
            System.out.println("3. Đã huỷ (cancelled)");
            System.out.print("Chọn: ");
            
            int choice = Integer.parseInt(scanner.nextLine());
            String newStatus;
            
            switch (choice) {
                case 1: newStatus = "pending"; break;
                case 2: newStatus = "received"; break;
                case 3: newStatus = "cancelled"; break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, giữ nguyên trạng thái.");
                    return;
            }
            
            po.setStatus(newStatus);
            purchaseOrderDAO.updatePurchaseOrder(po);
            System.out.println("Cập nhật trạng thái thành công.");

        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật đơn mua: " + e.getMessage());
        }
    }

    private void cancelOrder() {
        try {
            System.out.print("Nhập mã đơn mua cần huỷ: ");
            int id = Integer.parseInt(scanner.nextLine());

            PurchaseOrder po = purchaseOrderDAO.getPurchaseOrderById(id);
            if (po == null) {
                System.out.println("Không tìm thấy đơn mua.");
                return;
            }

            System.out.println("Bạn có chắc muốn huỷ đơn hàng " + id + "? (y/n): ");
            String confirm = scanner.nextLine();
            
            if ("y".equalsIgnoreCase(confirm)) {
                po.setStatus("cancelled");
                purchaseOrderDAO.updatePurchaseOrder(po);
                System.out.println("Đã huỷ đơn hàng thành công.");
            } else {
                System.out.println("Huỷ bỏ thao tác.");
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi huỷ đơn mua: " + e.getMessage());
        }
    }

    private void viewAll() {
        try {
            List<PurchaseOrder> orders = purchaseOrderDAO.getAllPurchaseOrders();
            if (orders.isEmpty()) {
                System.out.println("Không có đơn hàng nào.");
                return;
            }
            
            System.out.println("\n========== DANH SÁCH ĐƠN MUA HÀNG ==========");
            System.out.printf("%-5s | %-12s | %-15s | %-10s\n", 
                    "ID", "NHÀ CUNG CẤP", "NGÀY DỰ KIẾN", "TRẠNG THÁI");
            System.out.println("----------------------------------------------");
            
            for (PurchaseOrder po : orders) {
                // Lấy tên nhà cung cấp
                String supplierName = getSupplierName(po.getSupplierId());
                
                System.out.printf("%-5d | %-12s | %-15s | %-10s\n",
                        po.getPoId(), 
                        supplierName, 
                        po.getExpectedDate(), 
                        po.getStatus());
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách đơn hàng: " + e.getMessage());
        }
    }
    
    private String getSupplierName(int supplierId) {
        String name = "ID: " + supplierId;
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            String query = "SELECT name FROM suppliers WHERE supplier_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, supplierId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                name = rs.getString("name");
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy tên nhà cung cấp: " + e.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return name;
    }
    
    private void viewOrderDetails() {
        System.out.print("Nhập mã đơn hàng cần xem chi tiết: ");
        try {
            int orderId = Integer.parseInt(scanner.nextLine());
            purchaseOrderDetailUI.viewDetailsByOrder(orderId);
        } catch (NumberFormatException e) {
            System.out.println("Vui lòng nhập số hợp lệ!");
        } catch (SQLException e) {
            System.out.println("Lỗi khi xem chi tiết đơn hàng: " + e.getMessage());
        }
    }
}
