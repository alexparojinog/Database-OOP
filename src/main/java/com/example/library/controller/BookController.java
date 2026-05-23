package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class BookController implements Initializable {
    @FXML private TextField txtId;
    @FXML private TextField txtBookNo;
    @FXML private TextField txtTitle;
    @FXML private TextField txtAuthor;
    @FXML private ComboBox<String> cmbGenre;
    @FXML private TextField txtYear;
    @FXML private ComboBox<String> cmbStatus;
    @FXML private TextField txtSearch;
    @FXML private TableView<Book> tableBooks;
    @FXML private TableColumn<Book, Integer> colId;
    @FXML private TableColumn<Book, String>  colBookNo;
    @FXML private TableColumn<Book, String>  colTitle;
    @FXML private TableColumn<Book, String>  colAuthor;
    @FXML private TableColumn<Book, String>  colGenre;
    @FXML private TableColumn<Book, Integer> colYear;
    @FXML private TableColumn<Book, String>  colStatus;

    private final BookRepository repository = new BookRepository();
    private final ObservableList<Book> bookList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBookNo.setCellValueFactory(new PropertyValueFactory<>("bookNo"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tableBooks.setItems(bookList);

        cmbGenre.setItems(FXCollections.observableArrayList(
            "Fiction","Non-Fiction","Science","History","Biography","Technology","Mathematics","Literature","Accounting","HR","Other"));
        cmbStatus.setItems(FXCollections.observableArrayList("Available","Borrowed","Reserved","Lost"));
        cmbStatus.setValue("Available");
        txtId.setEditable(false);
        txtBookNo.setEditable(false);

        tableBooks.getSelectionModel().selectedItemProperty().addListener((obs, old, sel) -> {
            if (sel != null) {
                txtId.setText(String.valueOf(sel.getId()));
                txtBookNo.setText(sel.getBookNo());
                txtTitle.setText(sel.getTitle());
                txtAuthor.setText(sel.getAuthor());
                cmbGenre.setValue(sel.getGenre());
                txtYear.setText(String.valueOf(sel.getYear()));
                cmbStatus.setValue(sel.getStatus());
            }
        });
        loadBooks();
    }

    private void loadBooks() {
        bookList.setAll(repository.getAllBooks());
        txtBookNo.setText(repository.generateBookNo());
    }

    @FXML private void handleAdd() {
        if (!validate()) return;
        if (repository.addBook(buildBook())) {
            alert(Alert.AlertType.INFORMATION, "Book added successfully!");
            handleClear();
        } else { alert(Alert.AlertType.ERROR, "Failed to add book."); }
    }

    @FXML private void handleUpdate() {
        if (txtId.getText().isEmpty()) { alert(Alert.AlertType.WARNING, "Select a book to update."); return; }
        if (!validate()) return;
        Book b = buildBook();
        b.setId(Integer.parseInt(txtId.getText()));
        if (repository.updateBook(b)) {
            alert(Alert.AlertType.INFORMATION, "Book updated successfully!");
            handleClear();
        } else { alert(Alert.AlertType.ERROR, "Failed to update book."); }
    }

    @FXML private void handleDelete() {
        if (txtId.getText().isEmpty()) { alert(Alert.AlertType.WARNING, "Select a book to delete."); return; }
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setHeaderText(null);
        confirm.setContentText("Are you sure you want to delete this book?");
        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (repository.deleteBook(Integer.parseInt(txtId.getText()))) {
                alert(Alert.AlertType.INFORMATION, "Book deleted successfully!");
                handleClear();
            } else { alert(Alert.AlertType.ERROR, "Failed to delete book."); }
        }
    }

    @FXML private void handleClear() {
        txtId.clear(); txtTitle.clear(); txtAuthor.clear();
        txtYear.clear(); txtSearch.clear();
        cmbGenre.setValue(null); cmbStatus.setValue("Available");
        tableBooks.getSelectionModel().clearSelection();
        loadBooks();
    }

    @FXML private void handleSearch() {
        String kw = txtSearch.getText().trim();
        bookList.setAll(kw.isEmpty() ? repository.getAllBooks() : repository.searchBooks(kw));
    }

    private Book buildBook() {
        Book b = new Book();
        b.setBookNo(txtBookNo.getText().trim());
        b.setTitle(txtTitle.getText().trim());
        b.setAuthor(txtAuthor.getText().trim());
        b.setGenre(cmbGenre.getValue());
        b.setYear(Integer.parseInt(txtYear.getText().trim()));
        b.setStatus(cmbStatus.getValue());
        return b;
    }

    private boolean validate() {
        if (txtTitle.getText().trim().isEmpty() || txtAuthor.getText().trim().isEmpty()
                || cmbGenre.getValue() == null || txtYear.getText().trim().isEmpty()
                || cmbStatus.getValue() == null) {
            alert(Alert.AlertType.WARNING, "Please fill in all fields.");
            return false;
        }
        try {
            int y = Integer.parseInt(txtYear.getText().trim());
            if (y < 1000 || y > 2100) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            alert(Alert.AlertType.WARNING, "Enter a valid year (e.g. 2024).");
            return false;
        }
        return true;
    }

    private void alert(Alert.AlertType type, String msg) {
        Alert a = new Alert(type); a.setHeaderText(null); a.setContentText(msg); a.showAndWait();
    }
}
