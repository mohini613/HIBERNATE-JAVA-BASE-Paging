package com.pagingST;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class MainST {
    public static void main(String[] args) {
        // Create SessionFactory and Session objects
        SessionFactory sf = HibernateUtill.getSessionFactory();
        Session session = sf.openSession();
        
        // Begin a transaction
        Transaction tx = session.beginTransaction();

        // Create some Teacher entities
        Teacher teacher1 = new Teacher();
        teacher1.setTid(1);
        teacher1.setTname("Mr. Smith");

        Teacher teacher2 = new Teacher();
        teacher2.setTid(2);
        teacher2.setTname("Ms. Johnson");

        // Save Teachers
        session.save(teacher1);
        session.save(teacher2);

        // Create some Student entities and assign them to teachers
        Student student1 = new Student();
        student1.setSid(1);
        student1.setName("John Doe");
        student1.setAddress("123 Main St");
        student1.setTeacher(teacher1);

        Student student2 = new Student();
        student2.setSid(2);
        student2.setName("Jane Roe");
        student2.setAddress("456 Elm St");
        student2.setTeacher(teacher1);

        Student student3 = new Student();
        student3.setSid(3);
        student3.setName("Jim Beam");
        student3.setAddress("789 Oak St");
        student3.setTeacher(teacher2);

        // Save Students
        session.save(student1);
        session.save(student2);
        session.save(student3);

        // Commit the transaction
        tx.commit();

        // Fetch and display the entries
        String q = "from Student"; // HQL query to fetch Student entities

        org.hibernate.query.Query query = session.createQuery(q);
        query.setFirstResult(1);
        query.setMaxResults(3);

        List<Student> list = query.list();

        for (Student l : list) {
            System.out.println(l.getSid());
            System.out.println(l.getName());
            System.out.println(l.getAddress());
            System.out.println("---------------------");
            System.out.println(l.getTeacher()); // Assuming toString() is overridden in Teacher class
        }

        // Close the session
        session.close();
    }
}
