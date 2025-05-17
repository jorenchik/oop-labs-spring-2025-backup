package jtm.activity17;

import jtm.activity16.PersonManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jtm.TestUtils.database;
import static jtm.TestUtils.password;
import static jtm.TestUtils.user;

/**
 * CRUD operations for Student entities.
 */
public class StudentManager extends PersonManager {
    protected Connection conn;
    private static final Logger logger = LogManager.getLogger(StudentManager.class);

    // use injected constants
    static final String user = "u00";
    static final String password = "u00";
    static final String database = "database00";

    public StudentManager() {
        super();
    }

    final void setConn(Connection conn) {
        this.conn = conn;
    }

    public Student findStudent(int id) {
        String sql = String.format(
            "SELECT id, firstName, lastName, year FROM %s.Student WHERE id = ?", database
        );
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("year")
                    );
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding student by ID", e);
        }
        return new Student(0, null, null, 0);
    }

    public List<Student> findStudent(String firstName, String lastName) {
        List<Student> result = new ArrayList<>();
        String sql = String.format(
            "SELECT id, firstName, lastName, year FROM %s.Student " +
            "WHERE firstName LIKE ? AND lastName LIKE ?", database
        );
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + firstName + "%");
            ps.setString(2, "%" + lastName + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new Student(
                    rs.getInt("id"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getInt("year")
                ));
            }
        } catch (SQLException e) {
            logger.error("Error finding student by name", e);
        }
        return result;
    }

    public boolean insertStudent(String firstName, String lastName, int year) {
        String sql = String.format(
            "INSERT INTO %s.Student (typeId, firstName, lastName, year) VALUES (?, ?, ?, ?)", database
        );
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Student.typeId);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setInt(4, year);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.error("Error inserting student", e);
            return false;
        }
    }

    public boolean insertStudent(Student student) {
        String sql = String.format(
            "INSERT INTO %s.Student (id, typeId, firstName, lastName, year) VALUES (?, ?, ?, ?, ?)", database
        );
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, student.getId());
            ps.setInt(2, Student.typeId);
            ps.setString(3, student.getFirstName());
            ps.setString(4, student.getLastName());
            ps.setInt(5, student.getYear());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.error("Error inserting student object", e);
            return false;
        }
    }

    public boolean updateStudent(Student student) {
        String sql = String.format(
            "UPDATE %s.Student SET firstName = ?, lastName = ?, year = ? WHERE id = ?", database
        );
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setInt(3, student.getYear());
            ps.setInt(4, student.getId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.error("Error updating student", e);
            return false;
        }
    }

    public boolean deleteStudent(int id) {
        String sql = String.format("DELETE FROM %s.Student WHERE id = ?", database);
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.error("Error deleting student", e);
            return false;
        }
    }
}
