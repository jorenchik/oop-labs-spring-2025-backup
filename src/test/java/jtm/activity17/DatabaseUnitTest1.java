package jtm.activity17;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(value=MethodSorters.NAME_ASCENDING)
public class DatabaseUnitTest1 {

    private StudentManager manager;

    private static final String DB_NAME = "database00";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
    private static final String USER = "u00";
    private static final String PASSWORD = "u00";

	public static void runSQLScript(String filePath) throws Exception {
		String sql = Files.lines(Paths.get(filePath))
					  .filter(line -> !line.trim().startsWith("--")) // remove comments
					  .collect(Collectors.joining("\n"));

		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
			Statement stmt = conn.createStatement();

			// Split SQL by semicolon for individual statements
			for (String statement : sql.split(";")) {
				statement = statement.trim();
				if (!statement.isEmpty()) {
					stmt.execute(statement);
				}
			}
		} 
	}

    @BeforeClass
    public static void setUpClass() throws Exception {
		runSQLScript("src/main/java/jtm/activity16/database.sql");
    }

    @Before
    public void setUp() throws Exception {
        manager = new StudentManager();
    }

    @Test
    public final void test01StudentManager() {
        assertNotNull(manager);
    }

    @Test
    public final void test02FindStudentInt() {
        Student s = manager.findStudent(1);
        assertEquals("Mara", s.getFirstName());
        assertEquals("Ett", s.getLastName());

        Student notFound = manager.findStudent(999);
        assertEquals(0, notFound.getId());
        assertNull(notFound.getFirstName());
    }

    @Test
    public final void test03FindStudentStringString() {
        List<Student> found = manager.findStudent("Sara", "Du");
        assertEquals(1, found.size());
        assertEquals("Sara", found.get(0).getFirstName());

        List<Student> none = manager.findStudent("X", "Y");
        assertTrue(none.isEmpty());
    }

    @Test
    public final void test04InsertStudentStringStringInt() {
        boolean inserted = manager.insertStudent("Test", "Student", 42);
        assertTrue(inserted);

        Student s = manager.findStudent(42);
        assertEquals("Test", s.getFirstName());
    }

    @Test
    public final void test05InsertStudentStringStringIntException() {
        boolean inserted = manager.insertStudent(null, null, 100);
        assertTrue(!inserted);
    }

    @Test
    public final void test06InsertStudentStudent() {
        Student newStudent = new Student(55, "Anna", "Karenina");
        boolean inserted = manager.insertStudent(newStudent);
        assertTrue(inserted);

        Student s = manager.findStudent(55);
        assertEquals("Anna", s.getFirstName());
    }

    @Test
    public final void test07InsertStudentStudentException() {
        Student badStudent = new Student(1, null, null);
        boolean inserted = manager.insertStudent(badStudent);
        assertTrue(!inserted);
    }

    @Test
    public final void test08UpdateStudent() {
        Student s = manager.findStudent(1);
        Student updatedStudent = new Student(s.getId(), "Johnny", s.getLastName());
        boolean updated = manager.updateStudent(updatedStudent);
        assertTrue(updated);

        Student result = manager.findStudent(1);
        assertEquals("Johnny", result.getFirstName());
    }

    @Test
    public final void test09UpdateStudentException() {
        Student badStudent = new Student(9999, null, null);
        boolean updated = manager.updateStudent(badStudent);
        assertTrue(!updated);
    }

    @Test
    public final void test10DeleteStudent() {
        boolean deleted = manager.deleteStudent(2);
        assertTrue(deleted);

        Student s = manager.findStudent(2);
        assertEquals(0, s.getId());
    }

    @Test
    public final void test11DeleteStudentException() {
        boolean deleted = manager.deleteStudent(9999);
        assertTrue(!deleted);
    }
}
