package UI.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import util.DBConnection;

public class ViewBookUI {

    public static void show() {
        JFrame frame = new JFrame("View Books");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        String[] columns = {"ID", "Title", "Quantity"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);

        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        frame.add(panel);
        frame.setVisible(true);

        loadBooks(model);
    }

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
}