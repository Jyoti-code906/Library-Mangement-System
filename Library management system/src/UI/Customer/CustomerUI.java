package UI.Customer;

import javax.swing.*;
import java.awt.*;

public class CustomerUI {

    public static void show(int customerId) {
        JFrame frame = new JFrame("Customer Panel");
        frame.setSize(400, 350);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel("Customer Panel");
        title.setBounds(120, 20, 200, 30);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(title);

        JButton viewBooksBtn = new JButton("View Books");
        viewBooksBtn.setBounds(110, 70, 180, 30);
        panel.add(viewBooksBtn);
        viewBooksBtn.addActionListener(e -> {
            ViewBookUI.show();
        });

        JButton myBooksBtn = new JButton("My Issued Books");
        myBooksBtn.setBounds(110, 110, 180, 30);
        panel.add(myBooksBtn);
        myBooksBtn.addActionListener(e -> {
            MyBooksUI.show(customerId);
        });

        JButton feedbackBtn = new JButton("Give Feedback");
        feedbackBtn.setBounds(110, 150, 180, 30);
        panel.add(feedbackBtn);
        feedbackBtn.addActionListener(e -> {
            FeedbackUI.show(customerId);
        });

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(110, 200, 180, 30);
        panel.add(backBtn);

        backBtn.addActionListener(e -> frame.dispose());

        frame.add(panel);
        frame.setVisible(true);
    }
}