package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        try {
            System.out.println("Creating new student object...");
            Student student = new Student("Daffy", "Duck", "daffy@luv2code.com");

            session.beginTransaction();

            System.out.println("Saving student...");
            System.out.println(student);
            session.save(student);

            session.getTransaction().commit();

            // find out the student's id: primary key
            System.out.println("Saved student. Generated id: " + student.getId());

            // now get a new session and start transaction
            session = sessionFactory.openSession();
            session.beginTransaction();

            // retrieve student based on the id: primary key
            System.out.println("\nGetting stuent with id: " + student.getId());

            Student myStudent = session.get(Student.class, student.getId());
            System.out.println("Get complete: " + myStudent);

            // commit the transaction
            session.getTransaction().commit();

            System.out.println("done!");

        } finally{
            session.close();
        }
    }
}
