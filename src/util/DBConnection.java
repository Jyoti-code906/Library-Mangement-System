package util;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library_db",
                "root",
                "your_Password"
            );
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
