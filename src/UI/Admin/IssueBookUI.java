package UI.Admin;

import Service.IssueManager;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class IssueBookUI {

    public static void show() {

        JFrame frame = new JFrame("Issue Book");
        frame.setSize(400, 350);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel("Issue Book");
        title.setBounds(130, 20, 200, 30);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(title);

        // Book ID
        JLabel bookLabel = new JLabel("Book ID:");
        bookLabel.setBounds(40, 80, 100, 25);
        panel.add(bookLabel);

        JTextField bookField = new JTextField();
        bookField.setBounds(160, 80, 160, 25);
        panel.add(bookField);

        // Customer ID
        JLabel customerLabel = new JLabel("Customer ID:");
        customerLabel.setBounds(40, 130, 100, 25);
        panel.add(customerLabel);

        JTextField customerField = new JTextField();
        customerField.setBounds(160, 130, 160, 25);
        panel.add(customerField);

        // Button
        JButton issueBtn = new JButton("Issue Book");
        issueBtn.setBounds(120, 220, 140, 35);
        panel.add(issueBtn);

        issueBtn.addActionListener(e -> {
            try {
                int bookId = Integer.parseInt(bookField.getText());
                int customerId = Integer.parseInt(customerField.getText());
                String fakeInput = bookId + "\n" + customerId + "\n";
                Scanner sc = new Scanner(fakeInput);
                String message = IssueManager.issueBook(sc);
                JOptionPane.showMessageDialog(
                        frame,
                        message
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