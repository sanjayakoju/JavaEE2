package DataFeeder;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.*;

import java.util.Arrays;
import java.util.List;

public class DataLoader {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab4");
    EntityManager em = emf.createEntityManager();
    EntityTransaction transaction = em.getTransaction();

    public void loadData() {
        // Publisher
        Publisher publisher1 = new Publisher();
        publisher1.setName("Manhattan");
        publisher1.setNumberOfEmployees(120);

        Publisher publisher2 = new Publisher();
        publisher2.setName("Times");
        publisher2.setNumberOfEmployees(340);

        // Address
        Address address1 = new Address();
        address1.setCity("Fairfield");
        address1.setState("Iowa");
        address1.setStreet("1000N 4th Street");
        address1.setZip(52257);

        Address address2 = new Address();
        address2.setState("Iowa");
        address2.setCity("Ottumwa");
        address2.setStreet("2340 S 5th Street");
        address2.setZip(12425);

        // Author
        Author author1 = new Author();
        author1.setName("Sanjaya Koju");
        author1.setAddress(address1);

        Author author2 = new Author();
        author2.setName("Rahul Niraula");
        author2.setAddress(address2);

        // Student
        Student student1 = new Student();
        student1.setName("Omkar Nath Chaudhary");
        student1.setGpa(2.5);

        Student student2 = new Student();
        student2.setName("Shrawan Adhikari");
        student2.setGpa(3.6);

        // Book
        Book book1 = new Book();
        book1.setAuthor(author1);
        book1.setPages(300);
        book1.setTitle("Beginner JAVA");
        book1.setPublisher(publisher2);
        book1.setStudent(student1);


        Book book2 = new Book();
        book2.setTitle("Enterprise Architecture");
        book2.setPages(345);
        book2.setPublisher(publisher1);
        book2.setAuthor(author1);
        book2.setStudent(student2);



        transaction.begin();
        em.persist(publisher1);
        em.persist(publisher2);
        em.persist(address1);
        em.persist(address2);
        em.persist(author1);
        em.persist(author2);
        em.persist(book1);
        em.persist(book2);
        em.persist(student1);
        em.persist(student2);
        transaction.commit();

    }
}
