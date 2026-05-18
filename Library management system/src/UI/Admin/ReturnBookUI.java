package UI.Admin;

import Service.ReturnManager;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class ReturnBookUI {

    public static void show() {

        JFrame frame = new JFrame("Return Book");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel("Return Book");
        title.setBounds(120, 20, 200, 30);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        panel.add(title);

        // Issue ID
        JLabel issueLabel = new JLabel("Issue ID:");
        issueLabel.setBounds(40, 100, 100, 25);

        panel.add(issueLabel);

        JTextField issueField = new JTextField();
        issueField.setBounds(140, 100, 180, 25);

        panel.add(issueField);

        // Return Button
        JButton returnBtn = new JButton("Return Book");
        returnBtn.setBounds(120, 180, 140, 35);

        panel.add(returnBtn);

        returnBtn.addActionListener(e -> {

            try {

                int issueId =
                        Integer.parseInt(issueField.getText());

                String fakeInput =
                        issueId + "\n";

                Scanner sc =
                        new Scanner(fakeInput);

                ReturnManager.returnBook(sc);

                JOptionPane.showMessageDialog(
                        frame,
                        "Return process completed!"
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