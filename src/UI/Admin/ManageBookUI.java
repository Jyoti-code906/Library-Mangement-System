package UI.Admin;
import util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ManageBookUI {

    public static void show() {
        JFrame frame = new JFrame("Manage Books");
        frame.setSize( 600, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        // Table
        String[] columns = {"ID", "Title", "Quantity"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Buttons panel
        JPanel btnPanel = new JPanel();

        JButton addBtn = new JButton("Add Book");
        JButton deleteBtn = new JButton("Delete Book");
        JButton refreshBtn = new JButton("Refresh");

        btnPanel.add(addBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(refreshBtn);

        panel.add(btnPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);

        // Load data
        loadBooks(model);

        // Add Book
        addBtn.addActionListener(e -> {
            AddBookUI.show();
        });

        // Delete Book
        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(frame, "Select a book first!");
                return;
            }

            int id = (int) model.getValueAt(row, 0);
            deleteBook(id);
            loadBooks(model);
        });

        // Refresh
        refreshBtn.addActionListener(e -> loadBooks(model));
    }

    // Load books into table
    public static void loadBooks(DefaultTableModel model) {
        try {
            model.setRowCount(0);

            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Book");

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getInt("quantity")
                });
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Delete book
    public static void deleteBook(int id) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM Book WHERE book_id=?");
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
