package jpa.service;

import entitymodels.Course;
import entitymodels.Student;
import dao.StudentDAO;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


public class StudentService implements StudentDAO {

    private EntityManager em;
    private EntityManagerFactory emf;


    public StudentService (){
        emf = Persistence.createEntityManagerFactory("SMS");
        em = emf.createEntityManager();

    }


    //Method to retrieve all students from the database and returns a list of them.
    @Override
    public List<Student> getAllStudents()
    {


        List<Student> allStudents = (List<Student>) em.createQuery("SELECT s FROM Student s").getResultList();


        return allStudents;

    }

    //Method which takes in a students email. If the email doesn't exist, alerts the user and exits the program.
    //If the email exists, searches the list of all students, checks to see if the given email matches and returns that student.
    @Override
    public Student getStudentByEmail(String sEmail) {
       List<Student> allStudent = getAllStudents();
       Student found = null;
           for(Student s: allStudent){
               if (s.getsEmail().equals(sEmail)){
                   found = s;
               }
           }
          if (found == null){
              System.out.println("Email Not Found! Exiting the program.");
              System.exit(0);
          }
          return found; }


    //Takes a user provided email/password. If the student is null, returns false.
    // If the student is found, compare the given password to the password stored in the database.
    // If it matches, validate the user otherwise return false.
    @Override
    public boolean validateStudent(String sEmail, String sPassword) {
        Student s = getStudentByEmail(sEmail);
        if (s == null || sPassword == null){
            return false;
        } else{
            String password = s.getsPass();
            if (password.equals(sPassword))
                return true;
            else return false;
        }

    }

    //Method to register a user to a course given an email and the course id.
    @Override
    public void registerStudentToCourse(String sEmail, int cId) {
        em.getTransaction().begin();


        //Checks if the student is currently enrolled in the course they've selected
        Query q = em.createNativeQuery("SELECT * FROM Student_Course WHERE sEmail = ? AND id = ?");
        q.setParameter(1,sEmail);
        q.setParameter(2,cId);
        List<Course> courses = (List<Course>) q.getResultList();

        //If the student isn't enrolled in the selected course, add them to the course.
        if (courses.isEmpty()){
            Query addCourse = em.createNativeQuery("INSERT INTO Student_Course (sEmail, id) VALUES (?,?)");
            addCourse.setParameter(1,sEmail);
            addCourse.setParameter(2,cId);
            addCourse.executeUpdate();
            em.getTransaction().commit();

            //If the student is already registered to the selected course, alert them and exit the program.
        } else System.out.println("You are already registered in that course!");


    }


    //Method to get a list of all of a student's currently enrolled courses
    @Override
    public List<Course> getStudentCourses(String sEmail) {

        List<Course> allCourses =  new ArrayList<>();

        Query q = em.createNativeQuery("Select course.* FROM course  " +
                "JOIN student_course ON course.id=student_course.id " +
                "JOIN student ON student.email=student_course.sEmail " +
                "WHERE student.email = ?1 " +
                "ORDER BY 1 ASC",Course.class);
        q.setParameter(1,sEmail);

        allCourses =  q.getResultList();


        return allCourses;

    }
}
