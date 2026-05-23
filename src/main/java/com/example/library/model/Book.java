package com.example.library.model;

import javafx.beans.property.*;

public class Book {
    private final IntegerProperty id     = new SimpleIntegerProperty();
    private final StringProperty bookNo  = new SimpleStringProperty();
    private final StringProperty title   = new SimpleStringProperty();
    private final StringProperty author  = new SimpleStringProperty();
    private final StringProperty genre   = new SimpleStringProperty();
    private final IntegerProperty year   = new SimpleIntegerProperty();
    private final StringProperty status  = new SimpleStringProperty();

    public Book() {}
    public Book(int id, String bookNo, String title, String author, String genre, int year, String status) {
        this.id.set(id); this.bookNo.set(bookNo); this.title.set(title);
        this.author.set(author); this.genre.set(genre); this.year.set(year); this.status.set(status);
    }

    public int getId() { return id.get(); }
    public IntegerProperty idProperty() { return id; }
    public void setId(int v) { id.set(v); }

    public String getBookNo() { return bookNo.get(); }
    public StringProperty bookNoProperty() { return bookNo; }
    public void setBookNo(String v) { bookNo.set(v); }

    public String getTitle() { return title.get(); }
    public StringProperty titleProperty() { return title; }
    public void setTitle(String v) { title.set(v); }

    public String getAuthor() { return author.get(); }
    public StringProperty authorProperty() { return author; }
    public void setAuthor(String v) { author.set(v); }

    public String getGenre() { return genre.get(); }
    public StringProperty genreProperty() { return genre; }
    public void setGenre(String v) { genre.set(v); }

    public int getYear() { return year.get(); }
    public IntegerProperty yearProperty() { return year; }
    public void setYear(int v) { year.set(v); }

    public String getStatus() { return status.get(); }
    public StringProperty statusProperty() { return status; }
    public void setStatus(String v) { status.set(v); }
}
