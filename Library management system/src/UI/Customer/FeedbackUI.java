package UI.Customer;

import util.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class FeedbackUI {

    public static void show(int customerId) {

        JFrame frame = new JFrame("Give Feedback");
        frame.setSize(450, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel("Feedback");
        title.setBounds(160, 20, 120, 30);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(title);

        // Feedback Area
        JLabel feedbackLabel = new JLabel("Your Feedback:");
        feedbackLabel.setBounds(40, 70, 120, 25);
        panel.add(feedbackLabel);

        JTextArea feedbackArea = new JTextArea();

        JScrollPane scrollPane =
                new JScrollPane(feedbackArea);

        scrollPane.setBounds(40, 100, 340, 100);

        panel.add(scrollPane);

        // Rating
        JLabel ratingLabel = new JLabel("Rating:");
        ratingLabel.setBounds(40, 220, 100, 25);
        panel.add(ratingLabel);

        String[] ratings = {
                "1",
                "2",
                "3",
                "4",
                "5"
        };

        JComboBox<String> ratingBox =
                new JComboBox<>(ratings);

        ratingBox.setBounds(120, 220, 80, 25);

        panel.add(ratingBox);

        // Submit Button
        JButton submitBtn = new JButton("Submit");

        submitBtn.setBounds(70, 290, 120, 35);

        panel.add(submitBtn);

        // Back Button
        JButton backBtn = new JButton("Back");

        backBtn.setBounds(220, 290, 120, 35);

        panel.add(backBtn);

        // SUBMIT ACTION
        submitBtn.addActionListener(e -> {

            try {

                String feedback =
                        feedbackArea.getText();

                int rating =
                        Integer.parseInt(
                                (String) ratingBox.getSelectedItem()
                        );

                Connection con =
                        DBConnection.getConnection();

                String query =
                        "INSERT INTO Feedback(customer_id, message, rating) " +
                                "VALUES (?, ?, ?)";

                PreparedStatement ps =
                        con.prepareStatement(query);

                ps.setInt(1, customerId);

                ps.setString(2, feedback);

                ps.setInt(3, rating);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(
                        frame,
                        "Feedback submitted!"
                );

                feedbackArea.setText("");

                ratingBox.setSelectedIndex(0);

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        frame,
                        ex.getMessage()
                );
            }
        });

        // BACK ACTION
        backBtn.addActionListener(e -> {
            frame.dispose();
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}