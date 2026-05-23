module com.example.library {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires io.github.cdimascio.dotenv.java;

    opens com.example.library.app to javafx.graphics;
    opens com.example.library.controller to javafx.fxml;
    opens com.example.library.model to javafx.base;
}
