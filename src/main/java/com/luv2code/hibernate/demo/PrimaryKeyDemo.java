package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class PrimaryKeyDemo {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        try {

            System.out.println("Creating 3 student objects...");
            Student student1 = new Student("Mieczyslaw", "Nieprowokuj", "another@mail.com");
            Student student2 = new Student("Someone", "New", "yetanother@mail.com");
            Student student3 = new Student("Another", "One", "anotherone@mail.com");

            session.beginTransaction();
            session.save(student1);
            session.save(student2);
            session.save(student3);
            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }


    }

}
