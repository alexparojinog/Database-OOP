package com.example.library.app;

import com.example.library.factory.AuthWindowFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class LibraryApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        AuthWindowFactory.showLoginWindow(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}
