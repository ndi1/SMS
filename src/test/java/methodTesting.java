import entitymodels.Student;
import jpa.service.CourseService;
import jpa.service.StudentService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class methodTesting {

    //Variable initialization for tests
    CourseService c;
    StudentService s;
    String sEmail;
    String sName;
    Student student;

    //Setup courseService for the testGetAllCourses test
    @Before
     public void setupCourses(){
     c = new CourseService();

}
    //Test to check the getAllCourses method
    @Test
    public void testGetAllCourses(){
        Assert.assertEquals(10,c.getAllCourses().size());
}

    //Setup studentService for the testGetAllStudents test
    @Before
    public void setupStudents(){
        s = new StudentService();

    }

    //Test to check the getAllStudents method
    @Test
    public void testGetAllStudents(){
        Assert.assertEquals(10,s.getAllStudents().size());
    }

    //Setup and assign variables for the testGetStudentByEmail test
    @Before
    public void setupStudent(){
        s = new StudentService();
        sEmail = "aiannitti7@is.gd";
        sName = "Alexandra Iannitti";
        student = s.getStudentByEmail(sEmail);

    }

    //Test to check the getStudentByEmail method
    @Test
    public void testGetStudentByEmail(){
        Assert.assertEquals(sName,student.getsName());
    }

}
