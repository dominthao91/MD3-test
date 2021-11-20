package service.student;

import connection.ConnectionSingleton;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentService implements IStudentService {
    Connection connection = ConnectionSingleton.getConnection();

    @Override
    public List<Student> showAll() {
        List<Student> studentList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from student");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("sID");
                String name = resultSet.getString("sName");
                String className = resultSet.getString("sClass");
                Student student = new Student(id, name, className);
                studentList.add(student);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return studentList;
    }

    @Override
    public Student findById(int id) {
        Student student = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from student where sID=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("sName");
                String className = resultSet.getString("sClass");
                student = new Student(id,name,className);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return student;
    }
}
