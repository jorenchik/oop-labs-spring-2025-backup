package jtm.extra06;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class PersonManager {

    private static final String PERSISTENCE_UNIT_NAME = "mysql-local";
    private static final EntityManagerFactory managerFactory;

    // Initialize EntityManagerFactory
    static {
        try {
            managerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize EntityManagerFactory");
        }
    }

    /**
     * Close the EntityManagerFactory.
     * This should be called when the application shuts down.
     */
    public static void close() {
        if (managerFactory != null) {
            managerFactory.close();
        }
    }

    /**
     * Save passed Person in the database
     *
     * @param person the Person object to persist
     * @return true on success, false on error (e.g. person already exists)
     */
    public boolean createPerson(Person person) {
        // Create entity manager
        try (EntityManager em = managerFactory.createEntityManager()) {
            // TODO search for already existing person and return false if exists
            // Start transaction
            em.getTransaction().begin();
            // TODO save person using persist(...) method
            // Commit transaction
            em.getTransaction().commit();
            return true;
        }
    }

    /**
     * Retrieve a Person by ID.
     *
     * @param id the ID of the Person to retrieve
     * @return the Person object, or null if not found
     */
    public Person getPersonById(int id) {
        // TODO create entity manager and retrieve person using find(Person.class, id) method
        return null;
    }

    /**
     * Retrieve all Persons from the database.
     *
     * @return a List of all Person objects
     */
    public List<Person> getAllPersons() {
        // TODO create entity manager and retrieve all persons using
        // createQuery("SELECT p FROM Person p", Person.class).getResultList() methods
        return null;
    }

    /**
     * Update an existing Person in the database.
     *
     * @param person the Person object with updated information
     * @return true on success, false on error (e.g. person doesn't exist)
     */
    public boolean updatePerson(Person person) {
        // TODO create entity manager and retrieve all persons using merge(person) method
        // Use transactions
        return false;
    }

    /**
     * Delete a Person from the database by their ID.
     *
     * @param id the ID of the Person to delete
     * @return true on success, false on error (e.g. person doesn't exist)
     */
    public boolean deletePerson(int id) {
        // TODO create entity manager
        // find person using find(Person.class, id) and delete person from database using remove(person)
        // methods of entity manager
        // use transactions
        return false;
    }
}
