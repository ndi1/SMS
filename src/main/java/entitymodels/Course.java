package entitymodels;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Setting up the Course entity
@Entity

public class Course {

    @Id
    @Column(name = "id")
    private Integer cId;
    @Column(name = "instructor")
    private String cInstructorName;
    @Column(name = "name")
    private String cName;


    @ManyToMany(mappedBy = "sCourses")
    private List<Student> students = new ArrayList<>();





//Constructors and Getters and Setters for the Course Entity
    public Course() {

    }

    public Course(Integer cId, String cName, String cInstructorName,List<Student> students) {
        this.cId = cId;
        this.cName = cName;
        this.cInstructorName = cInstructorName;
        this.students = students;
    }

    public Course(Integer cId, String cName, String cInstructorName) {
        this.cId = cId;
        this.cName = cName;
        this.cInstructorName = cInstructorName;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcInstructorName() {
        return cInstructorName;
    }

    public void setcInstructorName(String cInstructorName) {
        this.cInstructorName = cInstructorName;
    }
}
