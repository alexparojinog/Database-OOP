package com.example.library.controller;

import com.example.library.repository.AuthRepository;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class SignUpController {
    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private PasswordField txtConfirmPassword;

    private final AuthRepository authRepository = new AuthRepository();

    @FXML
    private void handleCreateAccount() {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        String confirm  = txtConfirmPassword.getText().trim();
        if (username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Please fill in all fields.");
            return;
        }
        if (!password.equals(confirm)) {
            showAlert(Alert.AlertType.WARNING, "Passwords do not match.");
            return;
        }
        if (authRepository.register(username, password)) {
            showAlert(Alert.AlertType.INFORMATION, "Account created! You can now log in.");
            closeWindow();
        } else {
            showAlert(Alert.AlertType.ERROR, "Username already exists.");
        }
    }

    @FXML
    private void handleCancel() { closeWindow(); }

    private void closeWindow() {
        ((Stage) txtUsername.getScene().getWindow()).close();
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
