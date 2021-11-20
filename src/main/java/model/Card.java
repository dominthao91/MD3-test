package model;

import java.time.LocalDate;
import java.util.Date;

public class Card {
    private int id;
    private boolean status;
    private Book book;
    private Student student;
    private String borrowDate;
    private String returnDate;

    public Card() {
    }

    public Card(boolean status, Book book, Student student, String borrowDate, String returnDate) {
        this.status = status;
        this.book = book;
        this.student = student;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public Card(int id, boolean status, Book book, Student student, String borrowDate, String returnDate) {
        this.id = id;
        this.status = status;
        this.book = book;
        this.student = student;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
