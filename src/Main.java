import Models.Priority;
import Models.Task;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaEE_ESGI_Tasks");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        /*
        Priority low = new Priority("Basse");
        Priority medium = new Priority("Moyenne");
        Priority high = new Priority("Elevée");
        Priority wow = new Priority("Ça craint sa mère !");

        Task task1 = new Task("Portes","Changer les Portes d'entrée des bâtiments A et B", Calendar.getInstance().getTime());
        Task task2 = new Task("Entrée","Fermer la porte la nuit quand il n'y pas personne", Calendar.getInstance().getTime());
        Task task3 = new Task("Mickaël","Exclure Mr AFONSO, en 4AL1 pour consommation illégale d'huile de palme", Calendar.getInstance().getTime());


        task1.setPriority(medium);
        task2.setPriority(wow);
        task3.setPriority(high);
        */

        /**
         * JPA - Hibernate interaction here
         * */

        tx.begin();

        /*
        em.persist(low);
        em.persist(medium);
        em.persist(high);
        em.persist(wow);

        em.persist(task1);
        em.persist(task2);
        em.persist(task3);
        */

        String QUERY = "select t from Task t where t.priority.id = 9";
        Query query = em.createQuery(QUERY);

        List<Task> tasks = query.getResultList();

        for(Task task : tasks){
            System.out.println(task.getPriority().getName());
        }

        tx.commit();

        em.close();
        emf.close();
    }
}
