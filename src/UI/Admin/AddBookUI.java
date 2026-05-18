package UI.Admin;

import Service.BookManager;

import javax.swing.*;
import java.awt.*;

public class AddBookUI {

    public static void show() {

        JFrame frame = new JFrame("Add Book");
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel("Add New Book");
        title.setBounds(120, 20, 200, 30);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(title);

        // Title
        JLabel titleLabel = new JLabel("Book Title:");
        titleLabel.setBounds(40, 80, 100, 25);
        panel.add(titleLabel);

        JTextField titleField = new JTextField();
        titleField.setBounds(150, 80, 180, 25);
        panel.add(titleField);

        // Author
        JLabel authorLabel = new JLabel("Author:");
        authorLabel.setBounds(40, 120, 100, 25);
        panel.add(authorLabel);

        JTextField authorField = new JTextField();
        authorField.setBounds(150, 120, 180, 25);
        panel.add(authorField);

        // Category
        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(40, 160, 100, 25);
        panel.add(categoryLabel);

        JTextField categoryField = new JTextField();
        categoryField.setBounds(150, 160, 180, 25);
        panel.add(categoryField);

        // Publication
        JLabel publicationLabel = new JLabel("Publication:");
        publicationLabel.setBounds(40, 200, 100, 25);
        panel.add(publicationLabel);

        JTextField publicationField = new JTextField();
        publicationField.setBounds(150, 200, 180, 25);
        panel.add(publicationField);

        // Quantity
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(40, 240, 100, 25);
        panel.add(quantityLabel);

        JTextField quantityField = new JTextField();
        quantityField.setBounds(150, 240, 180, 25);
        panel.add(quantityField);

        // Button
        JButton addBtn = new JButton("Add Book");
        addBtn.setBounds(120, 300, 140, 35);
        panel.add(addBtn);

        addBtn.addActionListener(e -> {

            try {

                String titleText = titleField.getText();
                String author = authorField.getText();
                String category = categoryField.getText();
                String publication = publicationField.getText();

                int qty = Integer.parseInt(quantityField.getText());

                BookManager.addBook(
                        titleText,
                        author,
                        category,
                        publication,
                        qty
                );

                JOptionPane.showMessageDialog(frame,
                        "Book added successfully!");

                frame.dispose();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(frame,
                        ex.getMessage());
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}