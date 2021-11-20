package service.bookService;

import connection.ConnectionSingleton;
import model.Book;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookService implements IBookService{
    Connection connection = ConnectionSingleton.getConnection();
    public  Book findByName(String name){
        Book book = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select *from book where bName=?");
            preparedStatement.setString(1,name);
            ResultSet resultSet =preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("bID");
                String author = resultSet.getString("bAuthor");
                String note = resultSet.getString("bNote");
                int quantity = resultSet.getInt("quantity");
                book = new Book(id,name,author,note,quantity);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return book;

    }
    @Override
    public void borrowBook(int id) {
    try {
        PreparedStatement preparedStatement = connection.prepareStatement("update book set quantity = quantity-1 where bID =?");
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    }

    @Override
    public void returnBook(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update book set quantity = quantity+1 where bID =?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Book> showAll() {
        List<Book>bookList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from book");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int bID = resultSet.getInt("bID");
                String name = resultSet.getString("bName");
                String note = resultSet.getString("bNote");
                String author = resultSet.getString("bAuthor");
                int quantity = resultSet.getInt("quantity");
                Book book = new Book(bID,name,author,note,quantity);
                bookList.add(book);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  bookList;
    }

    @Override
    public Book findById(int id) {
        Book book = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from book where bID=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("bName");
                String author = resultSet.getString("bAuthor");
                String note = resultSet.getString("bNote");
                int quantity = resultSet.getInt("quantity");
                book = new Book(id,name,author,note,quantity);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return book;
    }
}
