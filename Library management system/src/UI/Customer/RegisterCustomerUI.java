package UI.Customer;

import util.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterCustomerUI {

    public static void show() {

        JFrame frame = new JFrame("Customer Registration");
        frame.setSize(450, 450);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel("Customer Registration");
        title.setBounds(100, 20, 250, 30);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        panel.add(title);

        // Name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 80, 100, 25);

        panel.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(170, 80, 180, 25);

        panel.add(nameField);

        // Email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 130, 100, 25);

        panel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(170, 130, 180, 25);

        panel.add(emailField);

        // Phone
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(50, 180, 100, 25);

        panel.add(phoneLabel);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(170, 180, 180, 25);

        panel.add(phoneField);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 230, 100, 25);

        panel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(170, 230, 180, 25);

        panel.add(passwordField);

        // Register Button
        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(140, 310, 140, 35);

        panel.add(registerBtn);

        registerBtn.addActionListener(e -> {

            try {

                String name = nameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String password =
                        new String(passwordField.getPassword());

                Connection con =
                        DBConnection.getConnection();

                String query =
                        "INSERT INTO Customer(name, email, phone, password) " +
                                "VALUES (?, ?, ?, ?)";

                PreparedStatement ps =
                        con.prepareStatement(query);

                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, phone);
                ps.setString(4, password);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(
                        frame,
                        "Registration successful!"
                );

                frame.dispose();

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