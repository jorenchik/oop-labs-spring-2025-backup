package jtm.activity17;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static jtm.TestUtils.database;
import static jtm.TestUtils.password;
import static jtm.TestUtils.user;

import jtm.activity16.PersonManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * CRUD operations for Teacher entities.
 */
public class TeacherManager extends PersonManager {
    protected Connection conn;
    private static final Logger logger = LogManager.getLogger(TeacherManager.class);
    // these will be injected via TestUtils constants
    static final String user = "u00";
    static final String password = "u00";
    static final String database = "database00";

    public TeacherManager() {
        super();
    }

    final void setConn(Connection conn) {
        this.conn = conn;
    }

    /**
     * Returns a Teacher instance represented by the specified ID.
     * @param id the ID of teacher
     * @return a Teacher object or one with zero/null fields if not found
     */
    public Teacher findTeacher(int id) {
        String sql = String.format(
            "SELECT id, firstName, lastName, department FROM %s.Teacher WHERE id = ?", database
        );
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int tid = rs.getInt("id");
                    String fn = rs.getString("firstName");
                    String ln = rs.getString("lastName");
                    String dept = rs.getString("department");
                    return new Teacher(tid, fn, ln, dept);
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding teacher by ID", e);
        }
        return new Teacher(0, null, null, null);
    }

    /**
     * Returns a list of Teacher objects matching given first and last name (partial match).
     * @param firstName the first name of teacher.
     * @param lastName  the last name of teacher.
     * @return a list of Teacher objects, empty if none found
     */
    public List<Teacher> findTeacher(String firstName, String lastName) {
        String sql = String.format(
            "SELECT id, firstName, lastName, department FROM %s.Teacher " +
            "WHERE firstName LIKE ? AND lastName LIKE ?", database
        );
        List<Teacher> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + firstName + "%");
            ps.setString(2, "%" + lastName + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int tid = rs.getInt("id");
                    String fn = rs.getString("firstName");
                    String ln = rs.getString("lastName");
                    String dept = rs.getString("department");
                    list.add(new Teacher(tid, fn, ln, dept));
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding teacher by name", e);
        }
        return list;
    }

    /**
     * Inserts a new teacher using firstName, lastName, and department.
     * @return true if inserted successfully
     */
    public boolean insertTeacher(String firstName, String lastName, String department) {
        String sql = String.format(
            "INSERT INTO %s.Teacher(typeId, firstName, lastName, department) VALUES(?, ?, ?, ?)", database
        );
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Teacher.typeId);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setString(4, department);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.error("Error inserting teacher", e);
            return false;
        }
    }

    /**
     * Inserts a new teacher using a Teacher object (including given ID).
     * @return true if inserted successfully
     */
    public boolean insertTeacher(Teacher teacher) {
        String sql = String.format(
            "INSERT INTO %s.Teacher(id, typeId, firstName, lastName, department) VALUES(?, ?, ?, ?, ?)", database
        );
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, teacher.getId());
            ps.setInt(2, Teacher.typeId);
            ps.setString(3, teacher.getFirstName());
            ps.setString(4, teacher.getLastName());
            ps.setString(5, teacher.getDepartment());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.error("Error inserting teacher object", e);
            return false;
        }
    }

    /**
     * Updates an existing Teacher in the repository.
     * @return true if a row was updated
     */
    public boolean updateTeacher(Teacher teacher) {
        String sql = String.format(
            "UPDATE %s.Teacher SET firstName = ?, lastName = ?, department = ? WHERE id = ?", database
        );
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, teacher.getFirstName());
            ps.setString(2, teacher.getLastName());
            ps.setString(3, teacher.getDepartment());
            ps.setInt(4, teacher.getId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.error("Error updating teacher", e);
            return false;
        }
    }

    /**
     * Deletes an existing Teacher by ID.
     * @return true if a row was deleted
     */
    public boolean deleteTeacher(int id) {
        String sql = String.format(
            "DELETE FROM %s.Teacher WHERE id = ?", database
        );
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.error("Error deleting teacher", e);
            return false;
        }
    }
}
