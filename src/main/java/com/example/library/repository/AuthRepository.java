package com.example.library.repository;

import com.example.library.model.UserAccount;
import com.example.library.util.Database;
import java.sql.*;

public class AuthRepository {
    public boolean usernameExists(String username) {
        String sql = "SELECT COUNT(*) FROM user_accounts WHERE username = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean register(String username, String password) {
        if (usernameExists(username)) return false;
        String sql = "INSERT INTO user_accounts (username, password) VALUES (?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username); ps.setString(2, password);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public UserAccount authenticate(String username, String password) {
        String sql = "SELECT * FROM user_accounts WHERE username = ? AND password = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username); ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return new UserAccount(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
}
