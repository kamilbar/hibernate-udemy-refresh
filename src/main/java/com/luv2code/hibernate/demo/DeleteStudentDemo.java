package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            int studentId = 2;
            session.beginTransaction();

            Student deleteStudent = session.get(Student.class, studentId);
            // System.out.println("Deleting student...");
            // session.delete(deleteStudent);

            // alternate option
            session.createQuery("delete from Student where id=3").executeUpdate();

            session.getTransaction().commit();

            System.out.println("Done!");

        } finally{
            session.close();
        }
    }
}
