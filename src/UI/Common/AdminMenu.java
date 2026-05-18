package UI.Common;

import UI.Admin.AdminLoginUI;

import javax.swing.*;
import java.awt.*;

public class AdminMenu {

    public static void show() {

        JFrame frame = new JFrame("Admin Menu");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel("Admin");
        title.setBounds(150, 20, 150, 30);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        panel.add(title);

        // Login Button
        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(110, 90, 160, 35);

        panel.add(loginBtn);

        // Back Button
        JButton backBtn = new JButton("Back");
        backBtn.setBounds(110, 160, 160, 35);

        panel.add(backBtn);

        // Actions
        loginBtn.addActionListener(e -> {
            AdminLoginUI.show();
        });

        backBtn.addActionListener(e -> {
            frame.dispose();
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}