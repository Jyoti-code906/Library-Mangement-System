package UI.Common;

import UI.Customer.CustomerLoginUI;
import UI.Customer.RegisterCustomerUI;

import javax.swing.*;
import java.awt.*;

public class CustomerMenu {

    public static void show() {

        JFrame frame = new JFrame("Customer Menu");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel("Customer");
        title.setBounds(140, 20, 200, 30);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        panel.add(title);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(110, 80, 160, 35);

        panel.add(loginBtn);

        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(110, 140, 160, 35);

        panel.add(registerBtn);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(110, 200, 160, 35);

        panel.add(backBtn);

        // Actions
        loginBtn.addActionListener(e -> {
            CustomerLoginUI.show();
        });

        registerBtn.addActionListener(e -> {
            RegisterCustomerUI.show();
        });

        backBtn.addActionListener(e -> {
            frame.dispose();
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}