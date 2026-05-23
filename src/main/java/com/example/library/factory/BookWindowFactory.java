package com.example.library.factory;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class BookWindowFactory {
    public static void showBookWindow(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(
            BookWindowFactory.class.getResource("/com/example/library/fxml/book-view.fxml"));
        Scene scene = new Scene(loader.load(), 960, 640);
        stage.setTitle("Library Management System");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }
}
