package jpa.service;

import entitymodels.Course;
import dao.CourseDAO;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class CourseService implements CourseDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public CourseService(){
        emf = Persistence.createEntityManagerFactory("SMS");
        em = emf.createEntityManager();
    }


//Method to retrieve a list of all available courses
    @Override
    public List<Course> getAllCourses() {


List<Course> allCourses = (List<Course>) em.createQuery("SELECT c FROM Course c").getResultList();

        return allCourses;
    }



}
