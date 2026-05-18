package UI.Admin;

import javax.swing.*;
import java.awt.*;

public class ManageStaffUI {

    public static void show() {

        JFrame frame = new JFrame("Manage Staff");
        frame.setSize(400, 350);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel("Staff Management");
        title.setBounds(110, 20, 200, 30);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(title);

        JButton addBtn = new JButton("Add Staff");
        addBtn.setBounds(100, 80, 180, 35);
        panel.add(addBtn);

        JButton viewBtn = new JButton("View Staff");
        viewBtn.setBounds(100, 140, 180, 35);
        panel.add(viewBtn);

        JButton deleteBtn = new JButton("Delete Staff");
        deleteBtn.setBounds(100, 200, 180, 35);
        panel.add(deleteBtn);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(100, 260, 180, 35);
        panel.add(backBtn);

        // ACTIONS

        addBtn.addActionListener(e -> {
            AddStaffUI.show();
        });

        viewBtn.addActionListener(e -> {
            ViewStaffUI.show();
        });

        deleteBtn.addActionListener(e -> {
            DeleteStaffUI.show();
        });

        backBtn.addActionListener(e -> {
            frame.dispose();
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}