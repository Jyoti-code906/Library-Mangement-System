package Service;
import util.*;
import java.sql.*;
import java.util.Scanner;

public class CustomerManager {
    public static void menu(Scanner sc) {
        while (true) {
            System.out.println("\n--- Customer Management ---");
            System.out.println("1. View Customers");
            System.out.println("2. Issue Library Card");
            System.out.println("3. Renew Library Card");
            System.out.println("4. Delete Customer");
            System.out.println("5. Back");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewCustomers();
                    break;
                case 2:
                    issueCard(sc);
                    break;
                case 3:
                    renewCard(sc);
                    break;
                case 4:
                    deleteCustomer(sc);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public static void viewCustomers() {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM Customer";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            System.out.println("\n--- Customer List ---");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("customer_id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getString("email") + " | " +
                                rs.getString("phone")
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void issueCard(Scanner sc) {
        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter customer_id: ");
            int customerId = sc.nextInt();

            String check = "SELECT * FROM Library_Card WHERE customer_id=?";
            PreparedStatement ps1 = con.prepareStatement(check);
            ps1.setInt(1, customerId);
            ResultSet rs = ps1.executeQuery();

            if (rs.next()) {
                System.out.println("Library card already exists!");
                return;
            }

            String query = "INSERT INTO Library_Card(customer_id, issue_date, expiry_date) " +
                    "VALUES (?, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 30 DAY))";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, customerId);
            ps.executeUpdate();

            System.out.println("Library card issued successfully!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void renewCard(Scanner sc) {
        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter customer_id: ");
            int customerId = sc.nextInt();

            String query = "UPDATE Library_Card " +
                    "SET expiry_date = DATE_ADD(CURDATE(), INTERVAL 90 DAY) " +
                    "WHERE customer_id=?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, customerId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Library card renewed successfully!");
            } else {
                System.out.println("Library card not found!");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void deleteCustomer(Scanner sc) {
        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter customer_id to delete: ");
            int id = sc.nextInt();

            // delete library card first
            String q1 = "DELETE FROM Library_Card WHERE customer_id=?";
            PreparedStatement ps1 = con.prepareStatement(q1);
            ps1.setInt(1, id);
            ps1.executeUpdate();

            // delete customer
            String q2 = "DELETE FROM Customer WHERE customer_id=?";
            PreparedStatement ps2 = con.prepareStatement(q2);
            ps2.setInt(1, id);
            ps2.executeUpdate();

            System.out.println("Customer deleted successfully!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}