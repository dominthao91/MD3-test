package controller;

import connection.ConnectionSingleton;
import model.Book;
import model.Card;
import model.Student;
import service.bookService.BookService;
import service.bookService.IBookService;
import service.card.CardService;
import service.card.ICardService;
import service.student.IStudentService;
import service.student.StudentService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "BookServlet", value = "/books")
public class BookServlet extends HttpServlet {
    Connection connection = ConnectionSingleton.getConnection();
    ICardService cardService = new CardService();
    IBookService bookService = new BookService();
    IStudentService studentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "borrow":
                showBorrow(request,response);
            default:
               listBook(request, response);
        }
    }

    private void showBorrow(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Book book = bookService.findById(id);
        List<Student>studentList = studentService.showAll();
        request.setAttribute("book",book);
        request.setAttribute("student",studentList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("card/borrow.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listBook(HttpServletRequest request, HttpServletResponse response) {
        List<Book> bookList = bookService.showAll();
        request.setAttribute("book", bookList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("card/listBook.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "borrow":
                borrowBook(request,response);
            default:
        }
    }

    private void borrowBook(HttpServletRequest request, HttpServletResponse response) {
        int bID = Integer.parseInt(request.getParameter("id"));
        Book book=bookService.findById(bID);
        int sID = Integer.parseInt(request.getParameter("student"));
        Student student=studentService.findById(sID);
        bookService.borrowBook(bID);
        boolean status = false;
        String borrow = request.getParameter("borrow");
        String returns = request.getParameter("return");
        Card card = new Card(status,book,student,borrow,returns);
        cardService.add(card);
        try {
            response.sendRedirect("/books");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
