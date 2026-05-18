package Service;
import util.*;
import java.sql.*;
import java.util.Scanner;

public class ReturnManager {

    public static void returnBook(Scanner sc) {
        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter issue_id: ");
            int issueId = sc.nextInt();

            // Check if issue exists
            String q1 = "SELECT book_id, expected_return_date, actual_return_date FROM Book_Issue WHERE issue_id=?";
            PreparedStatement ps1 = con.prepareStatement(q1);
            ps1.setInt(1, issueId);
            ResultSet rs = ps1.executeQuery();

            if (!rs.next()) {
                System.out.println("Invalid issue ID!");
                return;
            }

            // Check if already returned
            if (rs.getDate("actual_return_date") != null) {
                System.out.println("Book already returned!");
                return;
            }

            int bookId = rs.getInt("book_id");
            Date expectedDate = rs.getDate("expected_return_date");
            Date today = new Date(System.currentTimeMillis());

            // Calculate fine
            long diff = today.getTime() - expectedDate.getTime();
            int fine = 0;

            if (diff > 0) {
                long daysLate = diff / (1000 * 60 * 60 * 24);
                fine = (int) daysLate * 10;  // 10 per day
            }

            // Update Book_Issue
            String q2 = "UPDATE Book_Issue SET actual_return_date=CURDATE(), fine=? WHERE issue_id=?";
            PreparedStatement ps2 = con.prepareStatement(q2);
            ps2.setInt(1, fine);
            ps2.setInt(2, issueId);
            ps2.executeUpdate();

            // Increase book quantity
            String q3 = "UPDATE Book SET quantity = quantity + 1 WHERE book_id=?";
            PreparedStatement ps3 = con.prepareStatement(q3);
            ps3.setInt(1, bookId);
            ps3.executeUpdate();

            System.out.println("Book returned successfully!");
            System.out.println("Fine: " + fine);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}