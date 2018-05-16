import Models.Priority;
import Models.Task;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaEE_ESGI_Tasks");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Priority priority = new Priority("Moyenne");

        Task task = new Task("Portes","Changer les Portes d'entrée des bâtiments A et B", Calendar.getInstance().getTime());

        task.setPriority(priority);
        tx.begin();

        /**
         * JPA - Hibernate interaction here
         * */

        em.persist(priority);
        em.persist(task);
        tx.commit();
        em.close();
        emf.close();
    }
}
