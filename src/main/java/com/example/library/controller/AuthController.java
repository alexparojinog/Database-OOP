package com.example.library.controller;

import com.example.library.factory.AuthWindowFactory;
import com.example.library.factory.BookWindowFactory;
import com.example.library.model.UserAccount;
import com.example.library.repository.AuthRepository;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AuthController {
    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;

    private final AuthRepository authRepository = new AuthRepository();

    @FXML
    private void handleLogin() {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Please enter username and password.");
            return;
        }
        UserAccount account = authRepository.authenticate(username, password);
        if (account != null) {
            try {
                Stage stage = (Stage) txtUsername.getScene().getWindow();
                BookWindowFactory.showBookWindow(stage);
            } catch (Exception e) { e.printStackTrace(); }
        } else {
            showAlert(Alert.AlertType.ERROR, "Invalid username or password.");
        }
    }

    @FXML
    private void handleSignUp() {
        try { AuthWindowFactory.showSignUpWindow(); } catch (Exception e) { e.printStackTrace(); }
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
