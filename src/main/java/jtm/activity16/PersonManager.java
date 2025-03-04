package jtm.activity16;

import java.sql.Connection;
import java.util.List;
import static jtm.TestUtils.database;
import static jtm.TestUtils.password;
import static jtm.TestUtils.user;
public class PersonManager {
    protected Connection conn;
    // use user value from src/resources/application.properties file
    static final String user = "u00";
    // use password value from src/resources/application.properties file
    static final String password = "u00";
    // use database value from src/resources/application.properties file
    static final String database = "database00";

    public PersonManager() {
        /*-
         * TODO #1 When new PersonManager is created, create connection to the database server:
         * - url = "jdbc:mysql://localhost/?autoReconnect=true&serverTimezone=UTC&characterEncoding=utf8&allowMultiQueries=true"
         * - user = TestUtils.user
         * - pass = TestUtils.password
         * Notes:
         * 1. Use database name imported from jtm.TestUtils.database
         * 2. Do not pass database name into url, because some statements in tests need to be executed
         * server-wise, not just database-wise.
         * 3. Set AutoCommit to false and use conn.commit() where necessary in other methods
         */
    }

    public final Connection getConn() {
        return conn;
    }


    /**
     * Returns a Person instance represented by the specified ID.
     *
     * @param id the ID of person
     * @return a Person object
     */
    public final Person findPerson(int id) {
        // TODO #2 Write an sql statement that searches person by ID.
        // If person is not found return Person object with zero or null in
        // its fields!
        // Hint: Because default database is not set in connection,
        // use full notation for table "databaseXX.Person"
        return null;
    }

    /**
     * Returns a list of Person object that contain the specified first name and
     * last name. This will return an empty List of no match is found.
     *
     * @param firstName the first name of person.
     * @param lastName  the last name of person.
     * @return a list of Person object.
     */
    public final List<Person> findPerson(String firstName, String lastName) {
        /*- TODO #3 Write an sql statement that searches person by first and
         * last name and returns results as List<Person>.
         * Note that search results of partial match
         * in form ...like '%value%'... should be returned
         * Note, that if nothing is found return empty list!
         * Do not't use transactions for search (autocommit=false, commit() is not called)         * because table should not be blocked for concurrent write during search
         */
        return null;

    }

    /**
     * Insert a new person (first name and last name) into the repository.
     *
     * @param typeId    type of the person
     * @param firstName the first name of person
     * @param lastName  the last name of person
     * @return true if success, else false.
     */

    public final boolean insertPerson(int typeId, String firstName, String lastName) {
        // TODO #4 Write an sql statement that inserts person in database.
        return false;
    }

    /**
     * Insert person object into database
     *
     * @param person passed person object
     * @return true on success, false on error (e.g. non-unique id)
     */
    public final boolean insertPerson(Person person) {
        // TODO #5 Write an sql statement that inserts person in database.
        return false;
    }

    /**
     * Updates an existing Person in the repository with the values represented by
     * the Person object.
     *
     * @param person a Person object, which contain information for updating.
     * @return true if row was updated.
     */
    public final boolean updatePerson(Person person) {
        boolean status = false;
        // TODO #6 Write an sql statement that updates person information.
        return false;
    }

    /**
     * Delete an existing Person in the repository with the values represented by
     * the ID.
     *
     * @param id the ID of person.
     * @return true if row was deleted.
     */
    public final boolean deletePerson(int id) {
        // TODO #7 Write an sql statement that deletes person from database.
        return false;
    }

    public final void closeConnecion() {
        // TODO Close connection to the database server
    }
}
