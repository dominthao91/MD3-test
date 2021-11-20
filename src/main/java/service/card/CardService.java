package service.card;

import connection.ConnectionSingleton;
import model.Book;
import model.Card;
import model.Student;
import service.bookService.BookService;
import service.bookService.IBookService;
import service.student.IStudentService;
import service.student.StudentService;

import javax.naming.ldap.PagedResultsControl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CardService implements ICardService{
    Connection connection = ConnectionSingleton.getConnection();
    IBookService bookService = new BookService();
    IStudentService studentService = new StudentService();

    public List<Card> findByName(String name){
        List<Card>cardList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select *from card join student on student.sID = card.sID where name like %?");
        preparedStatement.setString(1,name);
        ResultSet resultSet =preparedStatement.executeQuery();
        while (resultSet.next()){
            int id = Integer.parseInt(resultSet.getString("cID"));
            int bid = Integer.parseInt(resultSet.getString("bID"));
            Book book = bookService.findById(bid);
            int sid = Integer.parseInt(resultSet.getString("sID"));
            Student student = studentService.findById(sid);
            boolean status =resultSet.getBoolean("status");
            String borrow = resultSet.getString("borrowDate");
            String returns = resultSet.getString("returnDate");
            Card card = new Card(id,status,book,student,borrow,returns);
            cardList.add(card);
        }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
return cardList;
    }
    @Override
    public List<Card> showAll() {
       List<Card>cardList =new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from card where status = false ");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("cID");
                int bookID = resultSet.getInt("bID");
                Book book = bookService.findById(bookID);
                int studentID = resultSet.getInt("sID");
                Student student = studentService.findById(studentID);
                boolean status = resultSet.getBoolean("status");
                String dateCheckIn = resultSet.getString("borrowDate");
                String dateCheckOut = resultSet.getString("returnDate");
                Card card = new Card(id,status,book,student,dateCheckIn,dateCheckOut);
                cardList.add(card);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  cardList;
    }

    @Override
    public Card findById(int id) {
        Card card = null;
try {
    PreparedStatement preparedStatement = connection.prepareStatement("select  * from  card where  cID=?");
preparedStatement .setInt(1,id);
ResultSet resultSet = preparedStatement .executeQuery();
while (resultSet.next()){
    int bookID = resultSet.getInt("bID");
    Book book = bookService.findById(bookID);
    int studentID = resultSet.getInt("sID");
    Student student = studentService.findById(studentID);
    boolean status = resultSet.getBoolean("status");
    String dateCheckIn = resultSet.getString("borrowDate");
    String dateCheckOut = resultSet.getString("returnDate");
    card = new Card(id,status,book,student,dateCheckIn,dateCheckOut);
}
} catch (SQLException throwables) {
    throwables.printStackTrace();
}
return card;
    }

    @Override
    public boolean add(Card card) {
        boolean rowAdd = false;
        try {
        PreparedStatement preparedStatement =
                connection.prepareStatement("insert  into card (bID,sID,borrowDate,returnDate,status) value (?,?,?,?,?)");
        preparedStatement.setInt(1,card.getBook().getId());
        preparedStatement.setInt(2,card.getStudent().getId());
        preparedStatement.setString(3,card.getBorrowDate());
        preparedStatement.setString(4,card.getReturnDate());
        preparedStatement.setBoolean(5,card.isStatus());
       rowAdd= preparedStatement.executeUpdate()>0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowAdd;
    }

    @Override
    public boolean edit( int id) {
        boolean rowEdit = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update card set status=true where cID =?");
            preparedStatement.setInt(1,id);
           rowEdit= preparedStatement.executeUpdate()>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowEdit;
    }
    @Override
    public boolean delete(int id) {
        boolean rowDelete = false;
try {
PreparedStatement preparedStatement = connection.prepareStatement("delete  from card where  cID =?");
preparedStatement.setInt(1,id);
rowDelete = preparedStatement.executeUpdate()>0;
} catch (SQLException throwables) {
    throwables.printStackTrace();
}
return rowDelete;
    }
}
