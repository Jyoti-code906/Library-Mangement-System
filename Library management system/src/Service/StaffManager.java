package Service;
import util.*;
import java.sql.*;
import java.util.Scanner;

public class StaffManager {

    public static void menu(Scanner sc) {
        while (true) {
            System.out.println("\n--- Staff Management ---");
            System.out.println("1. Add Staff");
            System.out.println("2. View Staff");
            System.out.println("3. Delete Staff");
            System.out.println("4. Back");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addStaff(sc);
                    break;
                case 2:
                    viewStaff();
                    break;
                case 3:
                    deleteStaff(sc);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public static void addStaff(Scanner sc) {
        try {
            Connection con = DBConnection.getConnection();

            sc.nextLine(); // clear buffer

            System.out.print("Enter name: ");
            String name = sc.nextLine();

            System.out.print("Enter role (admin/librarian): ");
            String role = sc.nextLine();

            System.out.print("Enter email: ");
            String email = sc.nextLine();

            System.out.print("Enter password: ");
            String password = sc.nextLine();

            System.out.print("Enter salary: ");
            float salary = sc.nextFloat();

            String query = "INSERT INTO Staff(name, role, email, password, salary) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, role);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setFloat(5, salary);

            ps.executeUpdate();

            System.out.println("Staff added successfully!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void viewStaff() {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM Staff";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            System.out.println("\n--- Staff List ---");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("staff_id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getString("role") + " | " +
                                rs.getString("email") + " | " +
                                rs.getFloat("salary")
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void deleteStaff(Scanner sc) {
        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter staff_id to delete: ");
            int id = sc.nextInt();

            String query = "DELETE FROM Staff WHERE staff_id=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Staff deleted!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}