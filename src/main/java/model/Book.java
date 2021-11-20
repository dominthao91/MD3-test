package model;

public class Book {
    private int id;
    private String name;
    private String author;
    private String note;
    private int quantity;


    public Book() {
    }

    public Book(int id, String name, String author, String note, int quantity) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.note = note;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
