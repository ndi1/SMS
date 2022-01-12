package dao;

import entitymodels.Course;
import entitymodels.Student;

import java.util.List;

public interface StudentDAO {

    public List<Student> getAllStudents();
    public Student getStudentByEmail(String sEmail);
    public boolean validateStudent(String sEmail, String sPassword);
    public void registerStudentToCourse(String sEmail, int cId);
    public List<Course> getStudentCourses(String sEmail);

}
