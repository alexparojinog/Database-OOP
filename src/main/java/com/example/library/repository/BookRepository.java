package com.example.library.repository;

import com.example.library.model.Book;
import com.example.library.util.Database;
import java.sql.*;
import java.util.*;

public class BookRepository {
    public List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM books ORDER BY id DESC")) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public List<Book> searchBooks(String keyword) {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE LOWER(title) LIKE ? OR LOWER(author) LIKE ? OR LOWER(genre) LIKE ? ORDER BY id DESC";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String k = "%" + keyword.toLowerCase() + "%";
            ps.setString(1, k); ps.setString(2, k); ps.setString(3, k);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public boolean addBook(Book b) {
        String sql = "INSERT INTO books (book_no, title, author, genre, year, status) VALUES (?,?,?,?,?,?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, b.getBookNo()); ps.setString(2, b.getTitle());
            ps.setString(3, b.getAuthor()); ps.setString(4, b.getGenre());
            ps.setInt(5, b.getYear());      ps.setString(6, b.getStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public boolean updateBook(Book b) {
        String sql = "UPDATE books SET book_no=?,title=?,author=?,genre=?,year=?,status=? WHERE id=?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, b.getBookNo()); ps.setString(2, b.getTitle());
            ps.setString(3, b.getAuthor()); ps.setString(4, b.getGenre());
            ps.setInt(5, b.getYear());      ps.setString(6, b.getStatus());
            ps.setInt(7, b.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public boolean deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id=?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public String generateBookNo() {
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM books")) {
            if (rs.next()) return String.format("LIB-%04d", rs.getInt(1) + 1);
        } catch (SQLException e) { e.printStackTrace(); }
        return "LIB-0001";
    }

    private Book mapRow(ResultSet rs) throws SQLException {
        return new Book(rs.getInt("id"), rs.getString("book_no"), rs.getString("title"),
            rs.getString("author"), rs.getString("genre"), rs.getInt("year"), rs.getString("status"));
    }
}
