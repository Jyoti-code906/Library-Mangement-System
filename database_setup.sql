-- =========================
-- DATABASE CREATION
-- =========================
CREATE DATABASE IF NOT EXISTS library_db;
USE library_db;

-- =========================
-- CUSTOMER TABLE
-- =========================
CREATE TABLE IF NOT EXISTS customer (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100) NOT NULL
);

-- =========================
-- STAFF TABLE
-- =========================
CREATE TABLE IF NOT EXISTS staff (
    staff_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100) NOT NULL
);

-- =========================
-- BOOK TABLE
-- =========================
CREATE TABLE IF NOT EXISTS book (
    book_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    author VARCHAR(100),
    quantity INT DEFAULT 1
);

-- =========================
-- ISSUE TABLE
-- =========================
CREATE TABLE IF NOT EXISTS issue (
    issue_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    book_id INT,
    issue_date DATE,
    return_date DATE,
    status VARCHAR(50) DEFAULT 'Issued',
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
    FOREIGN KEY (book_id) REFERENCES book(book_id)
);

-- =========================
-- FEEDBACK TABLE
-- =========================
CREATE TABLE IF NOT EXISTS feedback (
    feedback_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    message TEXT,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

-- =========================
-- SAMPLE DATA (OPTIONAL)
-- =========================
INSERT INTO book (title, author, quantity) VALUES
('Java Programming', 'Herbert Schildt', 5),
('Database System', 'Raghu Ramakrishnan', 3);