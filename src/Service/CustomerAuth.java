package Service;
import UI.Customer.CustomerUI;
import util.*;

import java.sql.*;
import java.util.Scanner;

public class CustomerAuth {

    // REGISTER
    public static void register(Scanner sc) {
        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter name: ");
            String name = sc.next();

            System.out.print("Enter email: ");
            String email = sc.next();

            System.out.print("Enter phone: ");
            String phone = sc.next();

            System.out.print("Enter password: ");
            String password = sc.next();

            String query = "INSERT INTO Customer(name, email, phone, password) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, password);

            ps.executeUpdate();

            System.out.println("Registration Successful!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // LOGIN
    public static void login(Scanner sc) {
        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter email: ");
            String email = sc.next();

            System.out.print("Enter password: ");
            String password = sc.next();

            String query = "SELECT * FROM Customer WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Login Successful!");
                CustomerUI.show(rs.getInt("customer_id"));
            } else {
                System.out.println("Invalid credentials!");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}