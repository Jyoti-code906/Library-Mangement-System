package Service;
import util.*;
import java.sql.*;
import java.util.Scanner;

public class IssueManager {

    public static String issueBook(Scanner sc) {
        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter book_id: ");
            int bookId = sc.nextInt();

            System.out.print("Enter customer_id: ");
            int customerId = sc.nextInt();

            // Check book availability
            String q1 = "SELECT quantity FROM Book WHERE book_id=?";
            PreparedStatement ps1 = con.prepareStatement(q1);
            ps1.setInt(1, bookId);
            ResultSet rs1 = ps1.executeQuery();

            if (!rs1.next()) {
                return "Book not found!";
            }

            if (rs1.getInt("quantity") <= 0) {
                return "Book not available!";
            }

            // Check customer exists
            String customerQuery =
                    "SELECT customer_id FROM Customer WHERE customer_id=?";

            PreparedStatement customerPs =
                    con.prepareStatement(customerQuery);

            customerPs.setInt(1, customerId);

            ResultSet customerRs = customerPs.executeQuery();

            if (!customerRs.next()) {

                return "Customer not found!";
            }

            // Check library card
            String q2 = "SELECT expiry_date FROM Library_Card WHERE customer_id=?";
            PreparedStatement ps2 = con.prepareStatement(q2);
            ps2.setInt(1, customerId);
            ResultSet rs2 = ps2.executeQuery();

            if (!rs2.next()) {
                return "Library card not found!";
            }

            Date expiry = rs2.getDate("expiry_date");
            Date today = new Date(System.currentTimeMillis());

            if (expiry.before(today)) {
                return "Library card expired!";
            }

            // Insert issue record
            String q3 = "INSERT INTO Book_Issue (book_id, customer_id, issue_date, expected_return_date) " +
                    "VALUES (?, ?, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 7 DAY))";

            PreparedStatement ps3 = con.prepareStatement(q3);
            ps3.setInt(1, bookId);
            ps3.setInt(2, customerId);
            ps3.executeUpdate();

            // Update book quantity
            String q4 = "UPDATE Book SET quantity = quantity - 1 WHERE book_id=?";
            PreparedStatement ps4 = con.prepareStatement(q4);
            ps4.setInt(1, bookId);
            ps4.executeUpdate();

            System.out.println("Book issued successfully!");

        } catch (Exception e) {
            return e.getMessage();
        }
        return "Issued successfully!";
    }
    public static void myBooks(int customerId) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT b.title, i.issue_date, i.expected_return_date, i.actual_return_date, i.fine " +
                    "FROM Book_Issue i JOIN Book b ON i.book_id = b.book_id " +
                    "WHERE i.customer_id=?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, customerId);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- My Books ---");

            while (rs.next()) {
                System.out.println(
                        rs.getString("title") + " | " +
                                rs.getDate("issue_date") + " | " +
                                rs.getDate("expected_return_date") + " | " +
                                rs.getDate("actual_return_date") + " | Fine: " +
                                rs.getInt("fine")
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}