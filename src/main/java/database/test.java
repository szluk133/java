package database;
import java.sql.Connection;
import java.sql.DriverManager;
public class test {
    public static void main(String[] args) {
        try {
            String url = "jdbc:mysql://localhost:3306/QLCHh?useSSL=false&serverTimezone=UTC";
            String user = "admin";
            String password = "123456";
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Kết nối thành công!");
            conn.close();
        } catch (Exception e) {
            System.err.println("❌ Kết nối thất bại:");
            e.printStackTrace();
        }
    }
}
