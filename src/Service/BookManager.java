package Service;
import util.*;
import java.sql.*;
import java.util.Scanner;

public class BookManager {

    // MENU
    public static void menu(Scanner sc) {
        while (true) {
            System.out.println("\n--- Book Management ---");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Delete Book");
            System.out.println("4. Back");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
//                    addBook(sc);
                    break;
                case 2:
                    viewBooks();
                    break;
                case 3:
                    deleteBook(sc);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    //ADD BOOK
    public static void addBook(
            String title,
            String authorName,
            String categoryName,
            String pubName,
            int qty
    ) {

        try {

            Connection con = DBConnection.getConnection();

            authorName = authorName.trim().toLowerCase();
            categoryName = categoryName.trim().toLowerCase();
            pubName = pubName.trim().toLowerCase();

            int authorId = getAuthorId(con, authorName);
            int categoryId = getCategoryId(con, categoryName);
            int pubId = getPublicationId(con, pubName);

            String query =
                    "INSERT INTO Book(title, author_id, category_id, publication_id, quantity) " +
                            "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, title);
            ps.setInt(2, authorId);
            ps.setInt(3, categoryId);
            ps.setInt(4, pubId);
            ps.setInt(5, qty);

            ps.executeUpdate();

            System.out.println("Book added successfully!");

        } catch (Exception e) {

            System.out.println(e);
        }
    }

    //AUTHOR HANDLER
    public static int getAuthorId(Connection con, String name) throws Exception {
        String select = "SELECT author_id FROM Author WHERE LOWER(name)=?"; 
        PreparedStatement ps = con.prepareStatement(select);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt("author_id");
        } else {
            String insert = "INSERT INTO Author(name) VALUES(?)";
            PreparedStatement ps2 = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ps2.setString(1, name);
            ps2.executeUpdate();

            ResultSet rs2 = ps2.getGeneratedKeys();
            rs2.next();
            return rs2.getInt(1);
        }
    }

    //CATEGORY HANDLER
    public static int getCategoryId(Connection con, String name) throws Exception {
        String select = "SELECT category_id FROM book_category WHERE LOWER(category_name)=?";
        PreparedStatement ps = con.prepareStatement(select);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt("category_id");
        } else {
            String insert = "INSERT INTO book_category(category_name) VALUES(?)";
            PreparedStatement ps2 = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ps2.setString(1, name);
            ps2.executeUpdate();

            ResultSet rs2 = ps2.getGeneratedKeys();
            rs2.next();
            return rs2.getInt(1);
        }
    }

    //PUBLICATION HANDLER
    public static int getPublicationId(Connection con, String name) throws Exception {
        String select = "SELECT publication_id FROM Publication WHERE LOWER(name)=?";
        PreparedStatement ps = con.prepareStatement(select);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt("publication_id");
        } else {
            String insert = "INSERT INTO Publication(name) VALUES(?)";
            PreparedStatement ps2 = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ps2.setString(1, name);
            ps2.executeUpdate();

            ResultSet rs2 = ps2.getGeneratedKeys();
            rs2.next();
            return rs2.getInt(1);
        }
    }

    //VIEW BOOKS
    public static void viewBooks() {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM Book";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            System.out.println("\n--- Book List ---");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("book_id") + " | " +
                                rs.getString("title") + " | Qty: " +
                                rs.getInt("quantity")
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //DELETE BOOK
    public static void deleteBook(Scanner sc) {
        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter book_id to delete: ");
            int id = sc.nextInt();

            String query = "DELETE FROM Book WHERE book_id=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Book deleted!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}