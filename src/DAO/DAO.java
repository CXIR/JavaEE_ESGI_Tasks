package DAO;

import Models.Priority;
import Models.Task;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class DAO {

    static DAO instance;
    EntityManagerFactory emf;
    EntityManager em;
    EntityTransaction tx;

    private DAO(){}

    public static DAO getInstance(){
        if(instance != null) return instance;
        return new DAO();
    }

    /**
     * Initilisation de la connexion à la BDD
     * A utiliser en début d'une opération CRUD
     */
    private void open() {
        this.emf = Persistence.createEntityManagerFactory("JavaEE_ESGI_Tasks");
        this.em = emf.createEntityManager();
        this.tx = em.getTransaction();
    }

    /**
     * Fermeture de la connexion à la BDD
     * A utiliser en fin d'une opération CRUD
     */
    private void close() {
        em.close();
        emf.close();
    }

    /** TASK **/

    // Création d'une tâche sans priorité
    public void createTask(String name, String description, Date deadline){
        open();
        try {
            tx.begin();
            Task task = new Task(name, description, deadline);
            em.persist(task);
            tx.commit();
        } finally {
            if (tx.isActive())
                tx.rollback();
            close();
        }

    }

    // Création d'une tâche avec priorité
    public void createTask(String name, String description, Date deadline, Priority priority){
        open();
        try {
            tx.begin();
            Task task = new Task(name, description, deadline);
            task.setPriority(priority);
            em.persist(task);
            tx.commit();
        } finally {
            if (tx.isActive())
                tx.rollback();
            close();
        }
    }

    // Récupère tout les tâches
    public List<Task> getAllTask() {
        List<Task> tasks;
        open();
        try {
            String QUERY = "from Task";
            Query query = em.createQuery(QUERY);
            tasks = query.getResultList();
        } finally {
            close();
        }

        return tasks;
    }

    // Récupère une tâche par son ID
    public Task getTaskByID(long ID){
        Task task;
        open();
        try {
            task = em.find(Task.class, ID);
        } finally {
            close();
        }

        return task;
    }

    // Mise à jour d'une tâche
    public Task update(Task task){
        open();
        try {
            tx.begin();
            em.merge(task);
            tx.commit();
        } finally {
            if (tx.isActive())
                tx.rollback();
            close();
        }
        return task;
    }

    public Task updateName(Task task, String name){

        open();

        try {
            tx.begin();

            task.setName(name);

            em.merge(task);
            tx.commit();

        } finally {

            if (tx.isActive())
                tx.rollback();
            close();
        }

        return task;
    }

    public Task updateDescription(Task task, String description){

        open();

        try {
            tx.begin();

            task.setDescription(description);

            em.merge(task);

            tx.commit();

        } finally {

            if (tx.isActive())
                tx.rollback();
            close();
        }

        return task;
    }

    public Task updateDeadline(Task task, Date date){

        open();

        try {
            tx.begin();

            task.setDeadline(date);

            em.merge(task);
            tx.commit();

        } finally {

            if (tx.isActive())
                tx.rollback();
            close();
        }

        return task;
    }

    public Task updatePriority(Task task, Priority priority){

        open();

        try {
            tx.begin();

            task.setPriority(priority);

            em.merge(task);
            tx.commit();

        } finally {

            if (tx.isActive())
                tx.rollback();
            close();
        }

        return task;
    }

    // Suppression d'une tâche
    public void deleteTask(Task task){
        open();
        try {
            tx.begin();

            Object managed = em.merge(task);
            em.remove(managed);

            tx.commit();
        } finally {
            if (tx.isActive())
                tx.rollback();
            close();
        }
    }

    /** PRIORITY **/

    // Récupère toutes les priorités
    public List<Priority> getAllPriority() {
        List<Priority> priorities;

        open();
        try {
            String QUERY = "from Priority";
            Query query = em.createQuery(QUERY);
            priorities = query.getResultList();
        } finally {
            close();
        }

        return priorities;
    }

    // Récupère une priorité par son ID
    public Priority getPriorityByID(long ID){
        Priority priority;

        open();
        try {
            priority = em.find(Priority.class, ID);
        } finally {
            close();
        }

        return priority;
    }

    public List<Task> getTaskByPriority(int priorityID) {

        List<Task> tasks;
        open();

        try {
            String QUERY = "select t from Task t where t.priority.id = " + priorityID;
            Query query = em.createQuery(QUERY);
            tasks = query.getResultList();
        } finally {
            close();
        }

        return tasks;

    }
}
