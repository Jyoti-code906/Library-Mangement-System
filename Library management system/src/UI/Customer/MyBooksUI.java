package UI.Customer;

import util.DBConnection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class MyBooksUI {

    public static void show(int customerId) {

        JFrame frame = new JFrame("My Issued Books");
        frame.setSize(700, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        String[] columns = {
                "Issue ID",
                "Book Title",
                "Issue Date",
                "Expected Return",
                "Actual Return",
                "Fine"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        JTable table = new JTable(model);

        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        frame.add(panel);
        frame.setVisible(true);

        loadBooks(model, customerId);
    }

    public static void loadBooks(DefaultTableModel model, int customerId) {

        try {

            model.setRowCount(0);

            Connection con = DBConnection.getConnection();

            String query =
                    "SELECT bi.issue_id, b.title, bi.issue_date, " +
                            "bi.expected_return_date, bi.actual_return_date, bi.fine " +
                            "FROM book_issue bi " +
                            "JOIN Book b ON bi.book_id = b.book_id " +
                            "WHERE bi.customer_id = ?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, customerId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                model.addRow(new Object[]{
                        rs.getInt("issue_id"),
                        rs.getString("title"),
                        rs.getDate("issue_date"),
                        rs.getDate("expected_return_date"),
                        rs.getDate("actual_return_date"),
                        rs.getDouble("fine")
                });
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}