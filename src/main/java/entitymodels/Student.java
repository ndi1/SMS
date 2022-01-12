package entitymodels;



import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//Setting up the student entity
@Entity
public class Student {


    @Id
    @Column(name = "email")
    private String sEmail;
    @Column(name = "name")
    private String sName;
    @Column(name = "password")
    private String sPass;



    @ManyToMany
    @JoinTable(name = "Student_Course",
            joinColumns = @JoinColumn(name = "sEmail", referencedColumnName = "email"),
            inverseJoinColumns = @JoinColumn(name = "id", referencedColumnName = "id"))
    private List<Course> sCourses =  new ArrayList<>();


//Constructors and Setters and Getters for the Student Entity
    public Student(String sEmail, String sName, String sPass, List<Course> sCourses) {
        this.sEmail = sEmail;
        this.sName = sName;
        this.sPass = sPass;
        this.sCourses = sCourses;
    }

    public List<Course> getsCourses() {
        return sCourses;
    }

    public void setsCourses(List<Course> sCourses) {
        this.sCourses = sCourses;
    }

    public Student() {
    }

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsPass() {
        return sPass;
    }

    public void setsPass(String sPass) {
        this.sPass = sPass;
    }

}
