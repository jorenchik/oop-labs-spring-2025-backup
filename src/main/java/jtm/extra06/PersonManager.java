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
     * Save passed Person in the database.
     *
     * @param person the Person object to persist
     * @return true on success, false on error (e.g. person already exists)
     */
    public boolean createPerson(Person person) {
        try (EntityManager em = managerFactory.createEntityManager()) {
            // Check if person already exists by ID
            if (em.find(Person.class, person.getId()) != null) {
                return false;
            }

            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieve a Person by ID.
     *
     * @param id the ID of the Person to retrieve
     * @return the Person object, or null if not found
     */
    public Person getPersonById(int id) {
        try (EntityManager em = managerFactory.createEntityManager()) {
            return em.find(Person.class, id);
        }
    }

    /**
     * Retrieve all Persons from the database.
     *
     * @return a List of all Person objects
     */
    public List<Person> getAllPersons() {
        try (EntityManager em = managerFactory.createEntityManager()) {
            return em.createQuery("SELECT p FROM Person p", Person.class).getResultList();
        }
    }

    /**
     * Update an existing Person in the database.
     *
     * @param person the Person object with updated information
     * @return true on success, false on error (e.g. person doesn't exist)
     */
    public boolean updatePerson(Person person) {
        try (EntityManager em = managerFactory.createEntityManager()) {
            Person existing = em.find(Person.class, person.getId());
            if (existing == null) {
                return false;
            }

            em.getTransaction().begin();
            em.merge(person);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete a Person from the database by their ID.
     *
     * @param id the ID of the Person to delete
     * @return true on success, false on error (e.g. person doesn't exist)
     */
    public boolean deletePerson(int id) {
        try (EntityManager em = managerFactory.createEntityManager()) {
            Person person = em.find(Person.class, id);
            if (person == null) {
                return false;
            }

            em.getTransaction().begin();
            em.remove(person);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
