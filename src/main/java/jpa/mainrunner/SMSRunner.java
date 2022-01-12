package jpa.mainrunner;

import entitymodels.Course;
import entitymodels.Student;
import helpers.TableFormat;
import jpa.service.CourseService;
import jpa.service.StudentService;


import java.util.*;


public class SMSRunner {

    public static void main(String[] args){


//Setting up classes used in the dialog interface
        Scanner scanner = new Scanner(System.in);
        StudentService ss = new StudentService();
        CourseService cs = new CourseService();
        TableFormat t = new TableFormat();


//Begin user interface
        System.out.println("Are you a(n)\n"+"1. Student\n"+"2. Quit\n"+"Please, enter 1 or 2.");

//Initial try catch block to check if a user is a student or not.
try{
    Integer studentChoice =  scanner.nextInt();
    if (studentChoice == 1){

        //Asking the user for their email and checking to see if their email is in the database.
        System.out.println("Enter Your Email:");
        String userEmail = scanner.next();
        ss.getStudentByEmail(userEmail);

        //Asking the user for their password. We then run the validateStudent method using their email/password.
        System.out.println("Enter Your Password:");
        String userPassword = scanner.next();
        if (ss.validateStudent(userEmail,userPassword)){

            //Once a user has been validated, we use their provided email to run the getStudentCourses method.
            List<List<String>> rows = new ArrayList<>();
            List<Course> studentCourses = ss.getStudentCourses(userEmail);

            //Building the output array
            List<String> headers = Arrays.asList("#","Course Name","Instructor Name");
            rows.add(headers);
            for (Course c:studentCourses
            ) {
                rows.add(Arrays.asList(c.getcId().toString(),c.getcName(),c.getcInstructorName()));
            }

            System.out.println("My Courses:");
            //Printing the array of all student's courses
            System.out.println(t.formatTable(rows));

            //Begin the prompt for a user to register for classes or log out
            System.out.println("1. Register to Class");
            System.out.println("2. Logout");

            //Try block for users registration input
            try{
                Integer register;
                register = scanner.nextInt();
                if (register == 1){
                    System.out.println("All Courses:");

                    //If user selects the register option, begin building the output array of available courses
                    List<List<String>> rowsAll = new ArrayList<>();
                    List<Course> coursesAll = cs.getAllCourses();
                    List<String> headersCourse = Arrays.asList("#","Course Name","Instructor Name");
                    rowsAll.add(headersCourse);
                    for (Course csa:coursesAll
                    ) {
                        rowsAll.add(Arrays.asList(csa.getcId().toString(),csa.getcName(),csa.getcInstructorName()));
                    }

                    //Printing the available courses array
                    System.out.println(t.formatTable(rowsAll));

                    //If a user inputs an option other than register, exit the program.
                } else{
                    System.out.println("Logging Out!");
                    System.exit(0);}
            } catch (InputMismatchException m){
               System.out.println("Incorrect course ID. Exiting the program");
               System.exit(0);
            }


            //Prompt the user to select a course to add
            System.out.println("Which Course Do You Want To Register For?");

            //Try block for users response
            try{
                Integer classToRegister;
                classToRegister = scanner.nextInt();

                //Once a user has input the desired course, we run the registerStudentToCourse method to add them
                ss.registerStudentToCourse(userEmail,classToRegister);

                //Begin building the student's updated course array
                List<List<String>> rowsUpdated = new ArrayList<>();
                List<Course> studentCoursesUpdated = ss.getStudentCourses(userEmail);
                List<String> headersUpdated = Arrays.asList("#","Course Name","Instructor Name");
                rowsUpdated.add(headersUpdated);
                for (Course cu:studentCoursesUpdated
                ) {
                    rowsUpdated.add(Arrays.asList(cu.getcId().toString(),cu.getcName(),cu.getcInstructorName()));
                }

                System.out.println("Successfully Added to Course!\n");

                System.out.println("My Courses:");
                //Print out the student's updated course list
                System.out.println(t.formatTable(rowsUpdated));

                //End the program and sign the user out
                System.out.println("You have been signed out.");

            } catch (InputMismatchException me){
                System.out.println("Incorrect course ID. Exiting the program.");
            }

        }
        //If the user provides the incorrect password, alert them and end the program.
        else{
            System.out.println("Incorrect Login Information. Please try again.");
            System.exit(0);
        }
    }
    //End the program if the user selects the quit option
    else
        System.out.println("Exiting the program!");
        System.exit(0);

} catch (InputMismatchException i){
    System.out.println("Unavailable Choice. Exiting the program.");
}

        }


    }



