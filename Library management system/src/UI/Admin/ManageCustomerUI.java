package UI.Admin;

import Service.CustomerManager;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class ManageCustomerUI {

    public static void show() {

        JFrame frame = new JFrame("Manage Customers");
        frame.setSize(400, 420);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel("Customer Management");
        title.setBounds(80, 20, 250, 30);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        panel.add(title);

        // View Customers
        JButton viewBtn = new JButton("View Customers");
        viewBtn.setBounds(100, 80, 180, 35);

        panel.add(viewBtn);

        // Issue Card
        JButton issueBtn = new JButton("Issue Library Card");
        issueBtn.setBounds(100, 140, 180, 35);

        panel.add(issueBtn);

        // Renew Card
        JButton renewBtn = new JButton("Renew Library Card");
        renewBtn.setBounds(100, 200, 180, 35);

        panel.add(renewBtn);

        // Delete Customer
        JButton deleteBtn = new JButton("Delete Customer");
        deleteBtn.setBounds(100, 260, 180, 35);

        panel.add(deleteBtn);

        // Back
        JButton backBtn = new JButton("Back");
        backBtn.setBounds(100, 320, 180, 35);

        panel.add(backBtn);

        // ACTIONS

        // View Customers
        viewBtn.addActionListener(e -> {
            CustomerManager.viewCustomers();

            JOptionPane.showMessageDialog(
                    frame,
                    "Customer list printed in console."
            );
        });

        // Issue Card
        issueBtn.addActionListener(e -> {

            String input =
                    JOptionPane.showInputDialog(
                            frame,
                            "Enter Customer ID:"
                    );

            try {

                int customerId =
                        Integer.parseInt(input);

                String fakeInput =
                        customerId + "\n";

                Scanner sc =
                        new Scanner(fakeInput);

                CustomerManager.issueCard(sc);

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        frame,
                        ex.getMessage()
                );
            }
        });

        // Renew Card
        renewBtn.addActionListener(e -> {

            String input =
                    JOptionPane.showInputDialog(
                            frame,
                            "Enter Customer ID:"
                    );

            try {

                int customerId =
                        Integer.parseInt(input);

                String fakeInput =
                        customerId + "\n";

                Scanner sc =
                        new Scanner(fakeInput);

                CustomerManager.renewCard(sc);

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        frame,
                        ex.getMessage()
                );
            }
        });

        // Delete Customer
        deleteBtn.addActionListener(e -> {

            String input =
                    JOptionPane.showInputDialog(
                            frame,
                            "Enter Customer ID:"
                    );

            try {

                int customerId =
                        Integer.parseInt(input);

                String fakeInput =
                        customerId + "\n";

                Scanner sc =
                        new Scanner(fakeInput);

                CustomerManager.deleteCustomer(sc);

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        frame,
                        ex.getMessage()
                );
            }
        });

        // Back
        backBtn.addActionListener(e -> {
            frame.dispose();
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}