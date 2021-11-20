package service.bookService;

import model.Book;

import java.util.List;

public interface IBookService {
    List<Book> showAll();

    Book findById(int id);
    void borrowBook(int id);
    void returnBook(int id);
}
