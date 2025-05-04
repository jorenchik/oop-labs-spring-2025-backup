package jtm.activity16;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonManager {
    protected Connection conn;

    // Required constants (not imported)
    static final String user = "u00";
    static final String password = "u00";
    static final String database = "database00";

    public PersonManager() {
        try {
            String url = "jdbc:mysql://localhost/?autoReconnect=true&serverTimezone=UTC&characterEncoding=utf8&allowMultiQueries=true";
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public final Connection getConn() {
        return conn;
    }

    public final Person findPerson(int id) {
        String sql = "SELECT id, typeId, firstname, lastname FROM " + database + ".Person WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Person(
                        rs.getInt("id"),
                        rs.getInt("typeId"),
                        rs.getString("firstname"),
                        rs.getString("lastname"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Not found: return Person with null/zero fields
        return new Person(0, null, null);
    }

    public final List<Person> findPerson(String firstName, String lastName) {
        List<Person> result = new ArrayList<>();
        String sql = "SELECT id, typeId, firstname, lastname FROM " + database + ".Person " +
                     "WHERE firstname LIKE ? AND lastname LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + firstName + "%");
            stmt.setString(2, "%" + lastName + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(new Person(
                        rs.getInt("id"),
                        rs.getInt("typeId"),
                        rs.getString("firstname"),
                        rs.getString("lastname")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public final boolean insertPerson(int typeId, String firstName, String lastName) {
        String sql = "INSERT INTO " + database + ".Person (typeId, firstname, lastname) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, typeId);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            int affected = stmt.executeUpdate();
            conn.commit();
            return affected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ignore) {}
        }
        return false;
    }

    public final boolean insertPerson(Person person) {
        String sql = "INSERT INTO " + database + ".Person (id, typeId, firstname, lastname) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, person.getId());
            stmt.setInt(2, person.getTypeId());
            stmt.setString(3, person.getFirstName());
            stmt.setString(4, person.getLastName());
            int affected = stmt.executeUpdate();
            conn.commit();
            return affected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ignore) {}
        }
        return false;
    }

    public final boolean updatePerson(Person person) {
        String sql = "UPDATE " + database + ".Person SET typeId=?, firstname=?, lastname=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, person.getTypeId());
            stmt.setString(2, person.getFirstName());
            stmt.setString(3, person.getLastName());
            stmt.setInt(4, person.getId());
            int affected = stmt.executeUpdate();
            conn.commit();
            return affected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ignore) {}
        }
        return false;
    }

    public final boolean deletePerson(int id) {
        String sql = "DELETE FROM " + database + ".Person WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int affected = stmt.executeUpdate();
            conn.commit();
            return affected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ignore) {}
        }
        return false;
    }

    public final void closeConnecion() {
        try {
            if (conn != null && !conn.isClosed()) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
