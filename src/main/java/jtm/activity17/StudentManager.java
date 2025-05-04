package jtm.activity17;

import jtm.activity16.PersonManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManager extends PersonManager {

    static final String user = "u00";
    static final String password = "u00";
    static final String database = "database00";

    public StudentManager() {
        super();
    }
    
    final public void setConn(Connection conn) {
    	this.conn = conn;
    }

    public Student findStudent(int id) {
        String sql = "SELECT id, firstname, lastname FROM " + database + ".Person " +
                     "WHERE id = ? AND typeId = 1";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Student(
                        rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Student(0, null, null);
    }

    public List<Student> findStudent(String firstName, String lastName) {
        List<Student> results = new ArrayList<>();
        String sql = "SELECT id, firstname, lastname FROM " + database + ".Person " +
                     "WHERE typeId = 1 AND firstname LIKE ? AND lastname LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + firstName + "%");
            stmt.setString(2, "%" + lastName + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                results.add(new Student(
                        rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }


	public boolean insertStudent(String firstName, String lastName, int id) {
		String sql = "INSERT INTO " + database + ".Person (id, typeId, firstname, lastname) VALUES (?, 1, ?, ?)";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			stmt.setString(2, firstName);
			stmt.setString(3, lastName);
			return stmt.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


    public boolean insertStudent(Student student) {
        String sql = "INSERT INTO " + database + ".Person (id, typeId, firstname, lastname) VALUES (?, 1, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, student.getId());
            stmt.setString(2, student.getFirstName());
            stmt.setString(3, student.getLastName());
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateStudent(Student student) {
        String sql = "UPDATE " + database + ".Person SET firstname = ?, lastname = ? WHERE id = ? AND typeId = 1";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setInt(3, student.getId());
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteStudent(int id) {
        String sql = "DELETE FROM " + database + ".Person WHERE id = ? AND typeId = 1";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
