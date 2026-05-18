package UI.Admin;

import util.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewStaffUI {

    public static void show() {

        JFrame frame = new JFrame("View Staff");
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        String[] columns = {
                "Staff ID",
                "Name",
                "Role",
                "Email",
                "Salary"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel);
        loadStaff(model);
        frame.setVisible(true);
    }

    public static void loadStaff(DefaultTableModel model) {
        try {
            model.setRowCount(0);
            Connection con = DBConnection.getConnection();
            String query = "SELECT * FROM Staff";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("staff_id"),
                        rs.getString("name"),
                        rs.getString("role"),
                        rs.getString("email"),
                        rs.getFloat("salary")
                });
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}