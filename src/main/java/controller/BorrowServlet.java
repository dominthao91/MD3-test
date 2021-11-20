package controller;

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
import java.util.List;

@WebServlet(name = "BorrowServlet", value = "/borrows")
public class BorrowServlet extends HttpServlet {
    IBookService bookService = new BookService();
    IStudentService studentService = new StudentService();
    ICardService cardService = new CardService();
    CardService cardServices = new CardService();
    BookService bookServices = new BookService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "returnBook":
                returnBooks(request,response);
                break;
            case"search":
                searchByName(request,response);
                break;
            default:
                showBorrow(request, response);
        }
    }

    private void searchByName(HttpServletRequest request, HttpServletResponse response) {
        String name =request.getParameter("search");
        List<Card>cardList=cardServices.findByName(name);
        request.setAttribute("cardList",cardList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("card/borrowList.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void returnBooks(HttpServletRequest request, HttpServletResponse response) {
        int cID = Integer.parseInt(request.getParameter("id"));
        Card card = cardService.findById(cID);
        request.setAttribute("card",card);
        RequestDispatcher dispatcher = request.getRequestDispatcher("card/returnBook.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showBorrow(HttpServletRequest request, HttpServletResponse response) {
        List<Card>cardList=cardService.showAll();
        request.setAttribute("cardList",cardList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("card/borrowList.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
            case "returnBook":
                returnBook(request,response);
                break;
            default:
        }
    }

    private void returnBook(HttpServletRequest request, HttpServletResponse response) {
        int cID = Integer.parseInt(request.getParameter("id"));
        int bookID = Integer.parseInt(request.getParameter("idBook"));
        bookService.returnBook(bookID);
        cardService.edit(cID);
        try {
            response.sendRedirect("/borrows");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
