package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            List<Student> students = session.createQuery("from Student ").getResultList();
            List<Student> selectedStudents;

            System.out.println("\nAll students:");
//            students.forEach(System.out::println);
            displayStudents(students);

            selectedStudents = session.createQuery("from Student where " +
                    "lastName='Nieprowokuj' or firstName='Someone'").getResultList();
            System.out.println("\nSelected students (last name: Nieprowokuj or first name: Someone:)");
            displayStudents(selectedStudents);

            selectedStudents = session.createQuery("from Student where " +
                    "email like '%gmail.com'").getResultList();
            System.out.println("\nSelected students (email like gmail.com:)");
            displayStudents(selectedStudents);


            session.getTransaction().commit();


            System.out.println("Done!");
        } finally{
            session.close();
        }
    }

    private static void displayStudents(List<Student> students) {
        for (Student student: students){
            System.out.println(student);
        }
    }
}
