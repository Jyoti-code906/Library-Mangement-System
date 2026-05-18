package UI.Customer;

import util.DBConnection;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerLoginUI {
    public static void show() {
        JFrame frame = new JFrame("Customer Login");
        frame.setSize(400, 320);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel("Customer Login");
        title.setBounds(120, 20, 200, 30);
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

        // Login button
        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(120, 210, 140, 35);

        panel.add(loginBtn);

        loginBtn.addActionListener(e -> {
            try {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                Connection con = DBConnection.getConnection();
                String query =
                        "SELECT customer_id FROM Customer " +
                                "WHERE email=? AND password=?";

                PreparedStatement ps =
                        con.prepareStatement(query);

                ps.setString(1, email);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    int customerId =
                            rs.getInt("customer_id");

                    JOptionPane.showMessageDialog(
                            frame,
                            "Login successful!"
                    );

                    frame.dispose();

                    CustomerUI.show(customerId);

                } else {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Invalid email or password!"
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