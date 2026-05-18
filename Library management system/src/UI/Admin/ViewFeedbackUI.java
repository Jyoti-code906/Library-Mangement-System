package UI.Admin;

import util.DBConnection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewFeedbackUI {
    public static void show() {
        JFrame frame = new JFrame("View Feedback");
        frame.setSize(850, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        // TABLE COLUMNS
        String[] columns = {
                "Feedback ID",
                "Customer Name",
                "Message",
                "Rating"
        };
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        // BACK BUTTON
        JButton backBtn = new JButton("Back");

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(backBtn);
        panel.add(bottomPanel, BorderLayout.SOUTH);
        backBtn.addActionListener(e -> {
            frame.dispose();
        });
        frame.add(panel);
        loadFeedback(model);
        frame.setVisible(true);
    }

    public static void loadFeedback(DefaultTableModel model) {
        try {
            model.setRowCount(0);
            Connection con = DBConnection.getConnection();
            String query =
                    "SELECT f.feedback_id, " +
                            "c.name, " +
                            "f.message, " +
                            "f.rating " +
                            "FROM Feedback f " +
                            "JOIN Customer c " +
                            "ON f.customer_id = c.customer_id";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("feedback_id"),
                        rs.getString("name"),
                        rs.getString("message"),
                        rs.getInt("rating")
                });
            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage()
            );
        }
    }
}