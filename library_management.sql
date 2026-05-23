-- Library Management System

CREATE TABLE IF NOT EXISTS user_accounts (
    id       SERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS books (
    id      SERIAL PRIMARY KEY,
    book_no VARCHAR(20)  NOT NULL UNIQUE,
    title   VARCHAR(255) NOT NULL,
    author  VARCHAR(255) NOT NULL,
    genre   VARCHAR(100),
    year    INT,
    status  VARCHAR(50) DEFAULT 'Available'
    );

INSERT INTO user_accounts (username, password) VALUES ('admin', 'admin123');

INSERT INTO books (book_no, title, author, genre, year, status) VALUES
('LIB-0001', 'Introduction to Java', 'James Gosling', 'Technology', 2020, 'Available'),
('LIB-0002', 'Data Structures and Algorithms', 'Thomas H. Cormen', 'Technology', 2009, 'Borrowed'),
('LIB-0003', 'Clean Code', 'Robert C. Martin', 'Technology', 2008, 'Available'),
('LIB-0004', 'Introduction to Python', 'John Zelle', 'Technology', 2016, 'Reserved'),
('LIB-0005', 'Computer Networks', 'Andrew S. Tanenbaum', 'Technology', 2010, 'Available'),
('LIB-0006', 'Database System Concepts', 'Abraham Silberschatz', 'Technology', 2019, 'Borrowed'),
('LIB-0007', 'Artificial Intelligence: A Modern Approach', 'Stuart Russell', 'Technology', 2020, 'Available'),
('LIB-0008', 'The Pragmatic Programmer', 'David Thomas', 'Technology', 2019, 'Available'),
('LIB-0009', 'Accounting Principles', 'Jerry J. Weygandt', 'Accounting', 2018, 'Available'),
('LIB-0010', 'Financial Accounting', 'Walter T. Harrison', 'Accounting', 2017, 'Borrowed'),
('LIB-0011', 'Managerial Accounting', 'Ray H. Garrison', 'Accounting', 2020, 'Available'),
('LIB-0012', 'Cost Accounting', 'Charles T. Horngren', 'Accounting', 2015, 'Reserved'),
('LIB-0013', 'Intermediate Accounting', 'Donald E. Kieso', 'Accounting', 2019, 'Available'),
('LIB-0014', 'Human Resource Management', 'Gary Dessler', 'HR', 2020, 'Available'),
('LIB-0015', 'Managing Human Resources', 'Scott A. Snell', 'HR', 2018, 'Borrowed');

