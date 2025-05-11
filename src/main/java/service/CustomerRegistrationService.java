package service;

import dao.CustomerDAO;
import dao.UserDAO;
import model.Customer;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerRegistrationService {
    private final CustomerDAO customerDAO = new CustomerDAO();
    private final UserDAO userDAO = new UserDAO();

    /**
     * Đăng ký tài khoản khách hàng mới
     *
     * @param fullName Họ tên khách hàng
     * @param address  Địa chỉ
     * @param phone    Số điện thoại
     * @param email    Email
     * @param username Tên đăng nhập
     * @param password Mật khẩu
     * @return true nếu đăng ký thành công, false nếu thất bại
     */
    public boolean registerCustomer(String fullName, String address, String phone, String email,
                                   String username, String password) {
        try {
            // Kiểm tra username đã tồn tại chưa
            if (userDAO.checkUsernameExists(username)) {
                System.out.println(">> Tên đăng nhập đã tồn tại! Vui lòng chọn tên đăng nhập khác.");
                return false;
            }

            // Kiểm tra email/phone đã tồn tại chưa
            if (customerDAO.isEmailExists(email)) {
                System.out.println(">> Email đã tồn tại! Vui lòng sử dụng email khác.");
                return false;
            }

            if (customerDAO.isPhoneExists(phone)) {
                System.out.println(">> Số điện thoại đã tồn tại! Vui lòng sử dụng số điện thoại khác.");
                return false;
            }

            // Tạo customer mới
            Customer customer = new Customer(0, fullName, address, phone, email, null);
            int customerId = customerDAO.addCustomerWithReturn(customer);
            
            if (customerId <= 0) {
                System.out.println(">> Không thể tạo thông tin khách hàng!");
                return false;
            }

            // Tạo tài khoản user với role_id = 3 (khách hàng)
            User user = new User();
            user.setUsername(username);
            user.setPasswordHash(password); // Note: This should be hashed in production
            user.setRoleId(3); // Role for customer
            user.setCustomerId(customerId);
            
            boolean userCreated = userDAO.addUser(user);
            
            if (!userCreated) {
                // Rollback: Delete the customer if user creation fails
                customerDAO.deleteCustomer(customerId);
                System.out.println(">> Không thể tạo tài khoản!");
                return false;
            }

            return true;
        } catch (SQLException e) {
            System.err.println("Lỗi khi đăng ký tài khoản: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
} 