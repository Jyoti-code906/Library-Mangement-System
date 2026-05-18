package app;
import UI.Common.*;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        // Create frame
        JFrame frame = new JFrame("Library Management System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Create panel
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Title label
        JLabel title = new JLabel("Library Management System");
        title.setBounds(60, 30, 300, 30);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(title);

        // Admin button
        JButton adminBtn = new JButton("Admin");
        adminBtn.setBounds(130, 100, 120, 30);
        panel.add(adminBtn);
        adminBtn.addActionListener(e -> {
            AdminMenu.show();
        });

        // Customer button
        JButton customerBtn = new JButton("Customer");
        customerBtn.setBounds(130, 150, 120, 30);
        panel.add(customerBtn);
        customerBtn.addActionListener(e -> {
            CustomerMenu.show();
        });

        // Add panel to frame
        frame.add(panel);

        // Show frame
        frame.setVisible(true);
    }
}