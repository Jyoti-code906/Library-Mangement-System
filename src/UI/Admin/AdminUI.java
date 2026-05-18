package UI.Admin;

import javax.swing.*;
import java.awt.*;

public class AdminUI {

    public static void show() {
        JFrame frame = new JFrame("Admin Panel");
        frame.setSize(400, 450);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel("Admin Panel");
        title.setBounds(130, 20, 200, 30);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(title);

        JButton bookBtn = new JButton("Manage Books");
        bookBtn.setBounds(110, 70, 180, 30);
        panel.add(bookBtn);
        bookBtn.addActionListener(e -> {
            ManageBookUI.show();
        });

        JButton customerBtn = new JButton("Manage Customers");
        customerBtn.setBounds(110, 110, 180, 30);
        panel.add(customerBtn);
        customerBtn.addActionListener(e -> {
            ManageCustomerUI.show();
        });

        JButton issueBtn = new JButton("Issue Book");
        issueBtn.setBounds(110, 150, 180, 30);
        panel.add(issueBtn);
        issueBtn.addActionListener(e -> {
            IssueBookUI.show();
        });

        JButton returnBtn = new JButton("Return Book");
        returnBtn.setBounds(110, 190, 180, 30);
        panel.add(returnBtn);
        returnBtn.addActionListener(e -> {
            ReturnBookUI.show();
        });

        JButton feedbackBtn = new JButton("View Feedback");
        feedbackBtn.setBounds(110, 230, 180, 30);
        panel.add(feedbackBtn);
        feedbackBtn.addActionListener(e -> {
            ViewFeedbackUI.show();
        });

        JButton staffBtn = new JButton("Manage Staff");
        staffBtn.setBounds(110, 270, 180, 30);
        panel.add(staffBtn);
        staffBtn.addActionListener(e -> {
            ManageStaffUI.show();
        });

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(110, 320, 180, 30);
        panel.add(backBtn);

        backBtn.addActionListener(e -> frame.dispose());

        frame.add(panel);
        frame.setVisible(true);
    }
}