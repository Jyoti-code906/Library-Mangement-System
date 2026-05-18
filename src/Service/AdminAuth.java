package Service;
import UI.Admin.AdminUI;
import util.*;

import java.sql.*;
import java.util.Scanner;

public class AdminAuth {

    public static void login(Scanner sc) {
        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter name: ");
            String name = sc.next();

            System.out.print("Enter password: ");
            String password = sc.next();

            String query = "SELECT * FROM Staff WHERE name=? AND password=? AND role='admin' ";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Admin Login Successful!");
                AdminUI.show();
            } else {
                System.out.println("Invalid credentials!");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}