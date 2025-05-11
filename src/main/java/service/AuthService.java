package service;

import dao.UserDAO;
import model.User;

import java.util.Objects;

public class AuthService {
    private final UserDAO userDAO = new UserDAO();

    public User login(String username, String password) {
        User user = userDAO.findUserByUsername(username);
        if (user == null) {
            System.out.println("Tài khoản không tồn tại.");
            return null;
        }

        if (!Objects.equals(user.getPasswordHash(), password)) {
            System.out.println("Sai mật khẩu.");
            return null;
        }

        System.out.println("Đăng nhập thành công với vai trò: " + (
                user.getRoleId() == 1 ? "Quản lý" :
                        user.getRoleId() == 2 ? "Nhân viên" :
                                user.getRoleId() == 3 ? "Khách hàng" :
                                        "Không xác định"
        ));
        return user;
    }
}
