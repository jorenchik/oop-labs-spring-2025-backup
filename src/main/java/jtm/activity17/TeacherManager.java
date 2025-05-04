package jtm.activity17;

import jtm.activity16.PersonManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherManager extends PersonManager {

    static final String user = "u00";
    static final String password = "u00";
    static final String database = "database00";

    public TeacherManager() {
        super();
    }

    public Teacher findTeacher(int id) {
        String sql = "SELECT p.id, p.firstname, p.lastname, d.department " +
                     "FROM " + database + ".Person p " +
                     "JOIN " + database + ".PersonDepartment d ON p.id = d.personId " +
                     "WHERE p.id = ? AND p.typeId = 2";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Teacher(
                        rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("department")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Teacher(0, null, null, null);
    }

    public List<Teacher> findTeacher(String firstName, String lastName) {
        List<Teacher> results = new ArrayList<>();
        String sql = "SELECT p.id, p.firstname, p.lastname, d.department " +
                     "FROM " + database + ".Person p " +
                     "JOIN " + database + ".PersonDepartment d ON p.id = d.personId " +
                     "WHERE p.typeId = 2 AND p.firstname LIKE ? AND p.lastname LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + firstName + "%");
            stmt.setString(2, "%" + lastName + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                results.add(new Teacher(
                        rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("department")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public boolean insertTeacher(String firstName, String lastName, String department) {
        String personSql = "INSERT INTO " + database + ".Person (typeId, firstname, lastname) VALUES (2, ?, ?)";
        String departmentSql = "INSERT INTO " + database + ".PersonDepartment (personId, department) VALUES (?, ?)";

        try {
            conn.setAutoCommit(false);

            try (PreparedStatement personStmt = conn.prepareStatement(personSql, Statement.RETURN_GENERATED_KEYS)) {
                personStmt.setString(1, firstName);
                personStmt.setString(2, lastName);
                if (personStmt.executeUpdate() != 1) {
                    conn.rollback();
                    return false;
                }

                ResultSet keys = personStmt.getGeneratedKeys();
                if (keys.next()) {
                    int newId = keys.getInt(1);

                    try (PreparedStatement deptStmt = conn.prepareStatement(departmentSql)) {
                        deptStmt.setInt(1, newId);
                        deptStmt.setString(2, department);
                        if (deptStmt.executeUpdate() != 1) {
                            conn.rollback();
                            return false;
                        }
                    }
                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean insertTeacher(Teacher teacher) {
        String personSql = "INSERT INTO " + database + ".Person (id, typeId, firstname, lastname) VALUES (?, 2, ?, ?)";
        String departmentSql = "INSERT INTO " + database + ".PersonDepartment (personId, department) VALUES (?, ?)";

        try {
            conn.setAutoCommit(false);

            try (PreparedStatement personStmt = conn.prepareStatement(personSql)) {
                personStmt.setInt(1, teacher.getId());
                personStmt.setString(2, teacher.getFirstName());
                personStmt.setString(3, teacher.getLastName());
                if (personStmt.executeUpdate() != 1) {
                    conn.rollback();
                    return false;
                }

                try (PreparedStatement deptStmt = conn.prepareStatement(departmentSql)) {
                    deptStmt.setInt(1, teacher.getId());
                    deptStmt.setString(2, teacher.getDepartment());
                    if (deptStmt.executeUpdate() != 1) {
                        conn.rollback();
                        return false;
                    }
                }
                conn.commit();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean updateTeacher(Teacher teacher) {
        String personSql = "UPDATE " + database + ".Person SET firstname = ?, lastname = ? WHERE id = ? AND typeId = 2";
        String departmentSql = "UPDATE " + database + ".PersonDepartment SET department = ? WHERE personId = ?";

        try (PreparedStatement personStmt = conn.prepareStatement(personSql);
             PreparedStatement deptStmt = conn.prepareStatement(departmentSql)) {

            personStmt.setString(1, teacher.getFirstName());
            personStmt.setString(2, teacher.getLastName());
            personStmt.setInt(3, teacher.getId());

            deptStmt.setString(1, teacher.getDepartment());
            deptStmt.setInt(2, teacher.getId());

            return personStmt.executeUpdate() == 1 && deptStmt.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteTeacher(int id) {
        String deleteDept = "DELETE FROM " + database + ".PersonDepartment WHERE personId = ?";
        String deletePerson = "DELETE FROM " + database + ".Person WHERE id = ? AND typeId = 2";

        try {
            conn.setAutoCommit(false);

            try (PreparedStatement deptStmt = conn.prepareStatement(deleteDept);
                 PreparedStatement personStmt = conn.prepareStatement(deletePerson)) {

                deptStmt.setInt(1, id);
                deptStmt.executeUpdate();

                int affected = personStmt.executeUpdate();
                conn.commit();
                return affected == 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
