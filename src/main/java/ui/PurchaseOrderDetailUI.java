package ui;
import dao.DatabaseConnection;
import dao.PurchaseOrderDetailDAO;
import model.PurchaseOrderDetail;

import java.util.*;
import java.sql.*;
import java.time.LocalDate;

public class PurchaseOrderDetailUI {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PurchaseOrderDetailDAO detailDAO = new PurchaseOrderDetailDAO();

    public void menu() {
        while (true) {
            System.out.println("\n==== QUẢN LÝ CHI TIẾT ĐƠN MUA ====");
            System.out.println("1. Thêm chi tiết cho đơn mua");
            System.out.println("2. Xem chi tiết theo mã đơn mua");
            System.out.println("3. Xoá chi tiết");
            System.out.println("0. Quay lại");
            System.out.print("Chọn chức năng: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1: 
                        System.out.print("Nhập mã đơn mua để thêm chi tiết: ");
                        int orderId = Integer.parseInt(scanner.nextLine());
                        addItemsToOrder(orderId); 
                        break;
                    case 2: 
                        System.out.print("Nhập mã đơn mua để xem chi tiết: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        viewDetailsByOrder(id); 
                        break;
                    case 3: deleteDetail(); break;
                    case 0: return;
                    default: System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void addItemsToOrder(int orderId) throws SQLException {
        try {
            // Kiểm tra xem đơn hàng có tồn tại không
            Connection conn = DatabaseConnection.getConnection();
            String checkQuery = "SELECT * FROM purchase_orders WHERE po_id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setInt(1, orderId);
            ResultSet checkRs = checkStmt.executeQuery();
            
            if (!checkRs.next()) {
                System.out.println("Đơn hàng không tồn tại!");
                checkRs.close();
                checkStmt.close();
                conn.close();
                return;
            }
            
            checkRs.close();
            checkStmt.close();
            
            // Hiển thị danh sách sản phẩm có thể thêm vào đơn hàng
            System.out.println("\n----- DANH SÁCH SẢN PHẨM -----");
            String menuQuery = "SELECT item_id, name, cost_price FROM menu_items";
            PreparedStatement menuStmt = conn.prepareStatement(menuQuery);
            ResultSet menuRs = menuStmt.executeQuery();
            
            System.out.printf("%-5s | %-30s | %-10s\n", "ID", "TÊN SẢN PHẨM", "GIÁ");
            System.out.println("--------------------------------------------------");
            
            while (menuRs.next()) {
                System.out.printf("%-5d | %-30s | %,10.2f\n", 
                        menuRs.getInt("item_id"),
                        menuRs.getString("name"),
                        menuRs.getDouble("cost_price"));
            }
            
            menuRs.close();
            menuStmt.close();
            
            boolean continueAdding = true;
            
            while (continueAdding) {
                try {
                    System.out.print("\nNhập mã sản phẩm (item_id): ");
                    int itemId = Integer.parseInt(scanner.nextLine());
                    
                    // Kiểm tra sản phẩm có tồn tại không
                    String itemCheckQuery = "SELECT * FROM menu_items WHERE item_id = ?";
                    PreparedStatement itemCheckStmt = conn.prepareStatement(itemCheckQuery);
                    itemCheckStmt.setInt(1, itemId);
                    ResultSet itemCheckRs = itemCheckStmt.executeQuery();
                    
                    if (!itemCheckRs.next()) {
                        System.out.println("Sản phẩm không tồn tại!");
                        itemCheckRs.close();
                        itemCheckStmt.close();
                        continue;
                    }
                    
                    String itemName = itemCheckRs.getString("name");
                    double defaultPrice = itemCheckRs.getDouble("cost_price");
                    itemCheckRs.close();
                    itemCheckStmt.close();
                    
                    System.out.print("Nhập số lượng: ");
                    int quantity = Integer.parseInt(scanner.nextLine());
                    
                    System.out.print("Nhập đơn giá (Enter để dùng giá mặc định " + defaultPrice + "): ");
                    String priceInput = scanner.nextLine();
                    double unitPrice = priceInput.isEmpty() ? defaultPrice : Double.parseDouble(priceInput);
                    
                    // Tự động sử dụng ngày hiện tại cho ngày nhận
                    java.sql.Date receivedDate = java.sql.Date.valueOf(LocalDate.now());
                    
                    // Tạo đối tượng chi tiết đơn hàng
                    PurchaseOrderDetail detail = new PurchaseOrderDetail();
                    detail.setPoId(orderId);
                    detail.setItemId(itemId);
                    detail.setQuantity(quantity);
                    detail.setUnitPrice(unitPrice);
                    detail.setReceivedDate(receivedDate);
                    
                    // Lưu chi tiết đơn hàng
                    if (detailDAO.addDetail(detail)) {
                        System.out.println("Đã thêm " + quantity + " " + itemName + " vào đơn hàng.");
                    } else {
                        System.out.println("Không thể thêm sản phẩm vào đơn hàng!");
                    }
                    
                    System.out.print("Tiếp tục thêm sản phẩm? (y/n): ");
                    continueAdding = scanner.nextLine().toLowerCase().equals("y");
                    
                } catch (NumberFormatException e) {
                    System.out.println("Vui lòng nhập số hợp lệ!");
                } catch (SQLException e) {
                    System.out.println("Lỗi SQL: " + e.getMessage());
                    e.printStackTrace();
                }
            }
            
            conn.close();
            System.out.println("Đã hoàn thành nhập chi tiết đơn hàng #" + orderId);
            
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm chi tiết đơn hàng: " + e.getMessage());
            throw e;
        }
    }

    public void viewDetailsByOrder(int orderId) throws SQLException {
        System.out.println("\n========== CHI TIẾT ĐƠN HÀNG #" + orderId + " ==========");
        
        // Lấy thông tin đơn hàng
        Connection conn = DatabaseConnection.getConnection();
        
        // Lấy tên nhà cung cấp
        String orderQuery = "SELECT po.po_id, po.supplier_id, po.expected_date, po.status, s.name as supplier_name " +
                           "FROM purchase_orders po " +
                           "JOIN suppliers s ON po.supplier_id = s.supplier_id " +
                           "WHERE po.po_id = ?";
        
        PreparedStatement orderStmt = conn.prepareStatement(orderQuery);
        orderStmt.setInt(1, orderId);
        ResultSet orderRs = orderStmt.executeQuery();
        
        if (!orderRs.next()) {
            System.out.println("Không tìm thấy đơn hàng!");
            orderRs.close();
            orderStmt.close();
            conn.close();
            return;
        }
        
        String supplierName = orderRs.getString("supplier_name");
        String orderStatus = orderRs.getString("status");
        
        System.out.println("Nhà cung cấp: " + supplierName);
        System.out.println("Ngày giao dự kiến: " + orderRs.getDate("expected_date"));
        System.out.println("Trạng thái: " + orderStatus);
        System.out.println("\n----- CHI TIẾT CÁC SẢN PHẨM -----");
        
        orderRs.close();
        orderStmt.close();
        
        // Lấy chi tiết đơn hàng
        String detailQuery = "SELECT pod.pod_id, pod.item_id, mi.name as item_name, pod.quantity, " +
                            "pod.unit_price, pod.line_total, pod.received_date " +
                            "FROM purchase_order_details pod " +
                            "JOIN menu_items mi ON pod.item_id = mi.item_id " +
                            "WHERE pod.po_id = ?";
        
        PreparedStatement detailStmt = conn.prepareStatement(detailQuery);
        detailStmt.setInt(1, orderId);
        ResultSet detailRs = detailStmt.executeQuery();
        
        System.out.printf("%-5s | %-30s | %-8s | %-10s | %-12s | %-12s\n", 
                "ID", "SẢN PHẨM", "SỐ LƯỢNG", "ĐƠN GIÁ", "THÀNH TIỀN", "NGÀY NHẬN");
        System.out.println("------------------------------------------------------------------------------------");
        
        double totalAmount = 0;
        
        while (detailRs.next()) {
            double lineTotal = detailRs.getDouble("line_total");
            totalAmount += lineTotal;
            
            System.out.printf("%-5d | %-30s | %-8d | %,10.2f | %,12.2f | %s\n", 
                    detailRs.getInt("pod_id"),
                    detailRs.getString("item_name"),
                    detailRs.getInt("quantity"),
                    detailRs.getDouble("unit_price"),
                    lineTotal,
                    detailRs.getDate("received_date"));
        }
        
        System.out.println("------------------------------------------------------------------------------------");
        System.out.printf("TỔNG GIÁ TRỊ ĐƠN HÀNG: %,20.2f\n", totalAmount);
        
        detailRs.close();
        detailStmt.close();
        conn.close();
    }

    private void deleteDetail() throws SQLException {
        System.out.print("Nhập ID chi tiết cần xoá: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Bạn có chắc muốn xoá chi tiết này? (y/n): ");
        String confirm = scanner.nextLine();
        
        if ("y".equalsIgnoreCase(confirm)) {
            if (detailDAO.deleteDetail(id)) {
                System.out.println("Xoá thành công chi tiết #" + id);
            } else {
                System.out.println("Không tìm thấy chi tiết với ID đã nhập.");
            }
        } else {
            System.out.println("Đã huỷ thao tác xoá.");
        }
    }
}
