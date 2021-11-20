package service.student;

import model.Book;
import model.Student;

import java.util.List;

public interface IStudentService {
    List<Student> showAll();

    Student findById(int id);
}
