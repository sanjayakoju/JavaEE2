package DataFeeder;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import model.*;

import java.util.Arrays;
import java.util.List;

public class DataFetcher {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab4");
    static EntityManager em = emf.createEntityManager();
    static EntityTransaction transaction = em.getTransaction();

    //  Todo  1- Write a named query to return all books for a students with gpa >= 3.0.
    public void getAllBook() {
        transaction.begin();
        Query query = em.createNamedQuery("findStudent");
        query.setParameter("gpa", 3.0);
        System.out.println("1- Write a named query to return all books for a students with gpa >= 3.0.");
        List<Book> books = query.getResultList();
        System.out.println("1 " + books);
        transaction.commit();
    }

    //  Todo  2- Write a native query to return all book with >= 100 pages for students with gpa >= 3.0.
    public void getAllBookWithStudent() {
        String queryString = "select * from book as b join student as s on b.student_id = s.id where s.gpa >= 3.0 and b.pages >=100";
        Query query = em.createNativeQuery(queryString, Book.class);
        List<Book> books = query.getResultList();
        System.out.println(" 2- Write a native query to return all book with >= 100 pages for students with gpa >= 3.0.");
        System.out.println("2 " + books);
    }

    //  Todo  3- Write a criteria query to find the book with pages more than or equal to 120 pages and belonging to students with gpa >= 3.0 and written by an Author living in Iowa, and the book is published by a publisher with more than 100 employees.
    public void getAllBookWithCriteria() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> bookRoot = criteriaQuery.from(Book.class);
        criteriaQuery.select(bookRoot);

        Join<Book, Student> joinStudent = bookRoot.join("student");
        Join<Book, Author> joinAuthor = bookRoot.join("author");
        Join<Author, Address> joinAddress = joinAuthor.join("address");
        Join<Book, Publisher> joinPublisher = bookRoot.join("publisher");

        criteriaQuery.where(criteriaBuilder.ge(bookRoot.get("pages"), 120),
                criteriaBuilder.ge(joinStudent.get("gpa"), 3.0),
                criteriaBuilder.equal(joinAddress.get("state"), "Iowa"),
                criteriaBuilder.ge(joinPublisher.get("numberOfEmployees"), 100)
        );

        Query query = em.createQuery(criteriaQuery);
        List<Book> books = query.getResultList();
        System.out.println("3- Write a criteria query to find the book with pages more than or equal to 120 pages and belonging to students with gpa >= 3.0 and written by an Author living in Iowa, and the book is published by a publisher with more than 100 employees.");
        System.out.println("3 " + books);

    }

    // Todo 4- Enable optimistic locking on Student entity.

    // Todo 5- Enable pessimistic locking on the the named query in part 1.

    public void getBook() {
//      Book book = em.find(Book.class, 1L);
//      Author author = book.getAuthor();
//      System.out.println(author);
//      System.out.println(book);

//        String sql = "SELECT b from Book b";
//        Query query = em.createQuery(sql);
//        List<Book> bookss = query.getResultList();
//        System.out.println(bookss);

        Student student = em.find(Student.class, 1L);
        List<Book> books = student.getBooks();
        System.out.println(books);

        for (Book b : student.getBooks()) {
            System.out.println(b);
        }

        System.out.println(student);
    }
}
