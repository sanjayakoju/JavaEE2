import DataFeeder.DataFetcher;
import DataFeeder.DataLoader;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab4");
    static EntityManager em = emf.createEntityManager();
    static EntityTransaction transaction = em.getTransaction();

    public static void main(String[] args) {
        System.out.println("Application Start");
        DataLoader dataLoader = new DataLoader();
        dataLoader.loadData();
        System.out.println("Data Loaded Successfully");
        DataFetcher dataFetcher = new DataFetcher();
//        dataFetcher.getAllBook();
//        dataFetcher.getAllBookWithStudent();
//        dataFetcher.getAllBookWithCriteria();
        dataFetcher.getBook();

        System.out.println("Application Stop");
    }
}
