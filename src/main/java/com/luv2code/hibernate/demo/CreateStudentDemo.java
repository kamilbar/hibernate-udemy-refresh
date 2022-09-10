package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudentDemo {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        try {
            System.out.println("Creating new student object...");
            Student student = new Student("Mieczyslaw", "Brzeczyszczykiewicz", "some@email.com");

            session.beginTransaction();

            System.out.println("Saving student...");
            session.save(student);

            session.getTransaction().commit();


        } finally{
            session.close();
        }
    }
}
