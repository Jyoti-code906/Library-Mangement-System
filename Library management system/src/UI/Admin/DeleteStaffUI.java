package UI.Admin;

import util.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeleteStaffUI {

    public static void show() {

        JFrame frame = new JFrame("Delete Staff");
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel("Delete Staff");
        title.setBounds(120, 20, 200, 30);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        panel.add(title);

        // Staff ID
        JLabel idLabel = new JLabel("Staff ID:");
        idLabel.setBounds(40, 90, 100, 25);
        panel.add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(150, 90, 180, 25);
        panel.add(idField);

        // Delete Button
        JButton deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(120, 150, 140, 35);
        panel.add(deleteBtn);

        deleteBtn.addActionListener(e -> {
            try {
                int staffId = Integer.parseInt(idField.getText());
                Connection con = DBConnection.getConnection();
                String query = "DELETE FROM Staff WHERE staff_id=?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, staffId);
                int rows = ps.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(
                            frame,
                            "Staff deleted successfully!"
                    );
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(
                            frame,
                            "Invalid Staff ID!"
                    );
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        frame,
                        ex.getMessage()
                );
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}