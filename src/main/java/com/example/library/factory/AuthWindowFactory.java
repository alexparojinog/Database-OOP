package com.example.library.factory;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class AuthWindowFactory {
    public static void showLoginWindow(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(
            AuthWindowFactory.class.getResource("/com/example/library/fxml/login-view.fxml"));
        Scene scene = new Scene(loader.load(), 400, 320);
        stage.setTitle("Library Management System - Login");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void showSignUpWindow() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(
            AuthWindowFactory.class.getResource("/com/example/library/fxml/signup-view.fxml"));
        Scene scene = new Scene(loader.load(), 400, 360);
        stage.setTitle("Sign Up");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
