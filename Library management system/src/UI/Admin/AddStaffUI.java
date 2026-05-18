package UI.Admin;

import Service.StaffManager;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class AddStaffUI {

    public static void show() {

        JFrame frame = new JFrame("Add Staff");
        frame.setSize(450, 500);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel("Add Staff");
        title.setBounds(150, 20, 200, 30);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        panel.add(title);

        // Name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(40, 80, 120, 25);
        panel.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(170, 80, 200, 25);
        panel.add(nameField);

        // Role
        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setBounds(40, 130, 120, 25);
        panel.add(roleLabel);

        JTextField roleField = new JTextField();
        roleField.setBounds(170, 130, 200, 25);
        panel.add(roleField);

        // Email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(40, 180, 120, 25);
        panel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(170, 180, 200, 25);
        panel.add(emailField);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(40, 230, 120, 25);
        panel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(170, 230, 200, 25);
        panel.add(passwordField);

        // Salary
        JLabel salaryLabel = new JLabel("Salary:");
        salaryLabel.setBounds(40, 280, 120, 25);
        panel.add(salaryLabel);

        JTextField salaryField = new JTextField();
        salaryField.setBounds(170, 280, 200, 25);
        panel.add(salaryField);

        // Add Button
        JButton addBtn = new JButton("Add Staff");
        addBtn.setBounds(130, 360, 160, 40);
        panel.add(addBtn);

        // ACTION
        addBtn.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String role = roleField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                float salary = Float.parseFloat(salaryField.getText());
                String fakeInput =
                        name + "\n" +
                                role + "\n" +
                                email + "\n" +
                                password + "\n" +
                                salary;

                Scanner sc = new Scanner(fakeInput);
                StaffManager.addStaff(sc);
                JOptionPane.showMessageDialog(
                        frame,
                        "Staff added successfully!"
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