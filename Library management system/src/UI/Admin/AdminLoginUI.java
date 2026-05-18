package UI.Admin;

import util.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminLoginUI {

    public static void show() {

        JFrame frame = new JFrame("Admin Login");
        frame.setSize(400, 320);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel("Admin Login");
        title.setBounds(130, 20, 200, 30);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        panel.add(title);

        // Email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(40, 90, 100, 25);

        panel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(140, 90, 180, 25);

        panel.add(emailField);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(40, 140, 100, 25);

        panel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(140, 140, 180, 25);

        panel.add(passwordField);

        // Login Button
        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(120, 210, 140, 35);

        panel.add(loginBtn);

        loginBtn.addActionListener(e -> {

            try {

                String email = emailField.getText();

                String password =
                        new String(passwordField.getPassword());

                Connection con =
                        DBConnection.getConnection();

                String query =
                        "SELECT * FROM Staff " +
                                "WHERE email=? AND password=? AND role='admin'";

                PreparedStatement ps =
                        con.prepareStatement(query);

                ps.setString(1, email);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Admin login successful!"
                    );

                    frame.dispose();

                    AdminUI.show();

                } else {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Invalid admin credentials!"
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