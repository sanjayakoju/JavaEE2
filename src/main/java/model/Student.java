package model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@NamedQuery(
        name = "findStudent",
        query = "SELECT s FROM Student s where s.gpa > :gpa",
        lockMode = LockModeType.PESSIMISTIC_WRITE
)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Version
    private long version;
    private String name;
    private double gpa;
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Book> books;

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gpa=" + gpa +
                '}';
    }
}
